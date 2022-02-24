package com.foorun.unieat.service.member;

import com.foorun.unieat.annotation.Validation;
import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.dto.MemberSignIn;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignInService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    /**
     * 사용자 Sign-In
     * @param memberSignIn {@link MemberSignIn}
     * @return Generated JWT Token
     */
    @Validation
    @Transactional
    public JwtToken signIn(MemberSignIn memberSignIn) {
        MemberJpo memberJpo = ensureMember(memberSignIn.getEmail());
        JwtToken jwtToken = jwtProvider.createTokens(memberJpo.getEmail(), memberJpo.getNickname(), memberJpo.getRole());
        if (memberJpo.isEqualsPassword(memberSignIn.getPassword())) {
            memberJpo.latestSignInNow();
        }
        return jwtToken;
    }

    private MemberJpo ensureMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(UniEatForbiddenException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ensureMember(username)
                .asUserDetails();
    }
}
