package com.foorun.unieat.service.member;

import com.foorun.unieat.client.PigeonClient;
import com.foorun.unieat.client.payload.PigeonRequest;
import com.foorun.unieat.client.payload.PigeonResponse;
import com.foorun.unieat.domain.email.jpo.EmailVerificationCodeJpo;
import com.foorun.unieat.domain.email.repository.EmailVerificationCodeRepository;
import com.foorun.unieat.domain.member.dto.MemberSendVerifyEmailPayload;
import com.foorun.unieat.domain.member.dto.MemberVerifyEmailPayload;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import com.foorun.unieat.domain.school.repository.SchoolRepository;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.exception.notfound.UniEatResourceExpiryException;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberVerifyEmailService {
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;
    private final PigeonClient pigeonClient;
    private final SchoolRepository schoolRepository;
    private final MemberRepository memberRepository;

    /**
     * 이메일 인증 메일 발송
     */
    @Transactional
    public long send(MemberSendVerifyEmailPayload payload) {
        final int CODE_LENGTH = 10;
        final Long verificationCode = IdentifyGenerator.number(CODE_LENGTH);
        PigeonResponse<Void> pigeonResponse = pigeonClient
                .send(PigeonRequest.formSingleEmailOfVerificationCode(
                        payload.getVerifyType(), payload.getEmail(), verificationCode));

        if (pigeonResponse.isSuccess()) {
            throw new UniEatLogicalException();
        }
        emailVerificationCodeRepository.save(EmailVerificationCodeJpo.of(payload.getEmail(), String.valueOf(verificationCode)));
        return verificationCode;
    }

    /**
     * 이메일 인증 코드 확인
     */
    @Transactional(readOnly = true)
    public boolean verify(MemberVerifyEmailPayload payload) {
        EmailVerificationCodeJpo emailVerificationCodeJpo = emailVerificationCodeRepository
                .findById(payload.getEmail())
                .orElseThrow(UniEatResourceExpiryException::new);

        return emailVerificationCodeJpo.isEqualsCode(payload.getVerificationCode());
    }

    /**
     * 이메일 인증 코드 확인
     */
    @Transactional
    public long verifyAndSignup(MemberVerifyEmailPayload payload) {
        EmailVerificationCodeJpo emailVerificationCodeJpo = emailVerificationCodeRepository
                .findById(payload.getEmail())
                .orElseThrow(UniEatResourceExpiryException::new);
        boolean verify = emailVerificationCodeJpo.isEqualsCode(payload.getVerificationCode());
        if (!verify) {
            throw new UniEatLogicalException();
        }
        long memberId = IdentifyGenerator.number(10);
        MemberJpo memberJpo = MemberJpo.builder()
                .id(memberId)
                .email(payload.getEmail())
                .build();
        SchoolJpo schoolJpo = schoolRepository.findById(243347309L)
                .orElseThrow(UniEatNotFoundException::new);
        memberJpo.setSchool(schoolJpo);
        memberRepository.save(memberJpo);
        return memberId;
    }
}
