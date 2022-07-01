package com.foorun.unieat.service.member;

import com.foorun.unieat.annotation.Validation;
import com.foorun.unieat.domain.common.StatusType;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.history.member.jpo.HistoryMemberSignInJpo;
import com.foorun.unieat.domain.history.member.repository.HistoryMemberSignInRepository;
import com.foorun.unieat.domain.member.dto.MemberSignIn;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.report.repository.ReportRepository;
import com.foorun.unieat.domain.token.jpo.RefreshTokenJpo;
import com.foorun.unieat.domain.token.repository.RefreshTokenRepository;
import com.foorun.unieat.exception.UniEatBlockedUserException;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberSignInService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final HistoryMemberSignInRepository historyMemberSignInRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    /**
     * 사용자 Sign-In
     * @param memberSignIn {@link MemberSignIn}
     * @return Generated JWT Token
     */
    @Validation
    @Transactional
    public JwtToken signIn(MemberSignIn memberSignIn) {
        MemberJpo memberJpo = ensureMember(memberSignIn.getEmail());

        if (bCryptPasswordEncoder.matches(memberSignIn.getPassword(), memberJpo.getPassword())) {
            if(isBlockedMember(memberJpo)) throw new UniEatBlockedUserException();

            memberJpo.latestSignInNow();
            historyMemberSignInRepository.save(HistoryMemberSignInJpo.of(memberJpo.getId()));
            JwtToken jwtToken = jwtProvider.createTokens(memberJpo.asUserDetails());
            refreshTokenRepository.save(RefreshTokenJpo.tokenOf(jwtToken.getRefreshToken()));
            return jwtToken;
        }
        throw new UniEatNotFoundException();
    }

    private MemberJpo ensureMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(UniEatForbiddenException::new);
    }

    private MemberJpo ensureMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(UniEatForbiddenException::new);
    }

    /**
     * 신고 누적으로 정지된 유저인지 Boolean값으로 반환
     */
    @Transactional(propagation = Propagation.NESTED)
    private boolean isBlockedMember(MemberJpo memberJpo){
        if(memberJpo.getStatus().name().equals(StatusType.INACTIVE.name())){
            LocalDateTime memberBlockedTime = memberJpo.getUpdatedAt();
            if(LocalDateTime.now().isBefore(memberBlockedTime.plusDays(30))){ //정지된 날로부터 30일 이전이라면 로그인안됨
                return true;
            }
            else{
                //30일 지났으면 다시 활성화
                memberJpo.setStatus(StatusType.ACTIVE);
            }
        }
        else if(memberJpo.getStatus().name().equals(StatusType.REMOVED.name())){
            //영구 정지 유저
            return true;
        }

        return false;
    }
    /**
     *
     * @param memberId 기존 Long Type 의 MemberID가 String 타입으로 전달됨
     */
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return ensureMember(Long.parseLong(memberId))
                .asUserDetails();
    }
}
