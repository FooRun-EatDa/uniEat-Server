package com.foorun.unieat.service.member;

import com.foorun.unieat.client.PigeonClient;
import com.foorun.unieat.client.payload.PigeonRequest;
import com.foorun.unieat.client.payload.PigeonResponse;
import com.foorun.unieat.domain.email.jpo.EmailVerificationCodeJpo;
import com.foorun.unieat.domain.email.repository.EmailVerificationCodeRepository;
import com.foorun.unieat.domain.member.dto.MemberSendVerifyEmailPayload;
import com.foorun.unieat.domain.member.dto.MemberVerifyEmailPayload;
import com.foorun.unieat.exception.UniEatLogicalException;
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

    /**
     * 이메일 인증 메일 발송
     */
    @Transactional
    public void send(MemberSendVerifyEmailPayload payload) {
        final int CODE_LENGTH = 4;
        final Long verificationCode = IdentifyGenerator.number(CODE_LENGTH);
        PigeonResponse<Void> pigeonResponse = pigeonClient
                .send(PigeonRequest.formSingleEmailOfVerificationCode(
                        payload.getVerifyType(), payload.getEmail(), verificationCode));

        if (pigeonResponse.isSuccess()) {
            throw new UniEatLogicalException();
        }
        emailVerificationCodeRepository.save(EmailVerificationCodeJpo.of(payload.getEmail(), String.valueOf(verificationCode)));
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
}
