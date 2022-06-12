package com.foorun.unieat.service.member;

import com.foorun.unieat.domain.common.jwt.JwtToken;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.token.jpo.RefreshTokenJpo;
import com.foorun.unieat.domain.token.repository.RefreshTokenRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberTokenService {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public JwtToken reIssueToken(String refreshToken) {
        RefreshTokenJpo refreshTokenJpo = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new UniEatLogicalException("변조된 토큰이거나 알 수 없는 토큰입니다."));
        refreshTokenRepository.delete(refreshTokenJpo);
        if (jwtProvider.isExpiredToken(refreshToken)) {
            throw new UniEatLogicalException("유효기간이 만료된 토큰입니다.");
        }
        final Long memberId = jwtProvider.getMemberId(refreshToken);
        return createTokenByMemberId(memberId);
    }

    private JwtToken createTokenByMemberId(Long memberId) {
        MemberJpo memberJpo = ensureMember(memberId);
        JwtToken jwtToken = jwtProvider.createTokens(memberJpo.asUserDetails());
        refreshTokenRepository.save(RefreshTokenJpo.tokenOf(jwtToken.getRefreshToken()));
        return jwtToken;
    }

    private MemberJpo ensureMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(UniEatForbiddenException::new);
    }
}
