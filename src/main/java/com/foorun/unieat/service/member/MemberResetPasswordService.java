package com.foorun.unieat.service.member;

import com.foorun.unieat.domain.member.dto.MemberResetPasswordPayload;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberResetPasswordService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입 프로세스 - 회원 생성
     */
    @Transactional
    public void resetPassword(MemberResetPasswordPayload memberResetPasswordPayload) {
        MemberJpo memberJpo = ensureMember(memberResetPasswordPayload.getEmail());
        memberJpo.changePassword(memberJpo.getPassword());
    }

    private MemberJpo ensureMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(UniEatForbiddenException::new);
    }
}
