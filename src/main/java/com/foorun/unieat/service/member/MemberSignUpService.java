package com.foorun.unieat.service.member;

import com.foorun.unieat.annotation.Validation;
import com.foorun.unieat.client.PigeonClient;
import com.foorun.unieat.client.payload.PigeonRequest;
import com.foorun.unieat.client.payload.PigeonResponse;
import com.foorun.unieat.domain.email.jpo.EmailVerificationCodeJpo;
import com.foorun.unieat.domain.email.repository.EmailVerificationCodeRepository;
import com.foorun.unieat.domain.member.dto.MemberSignUp;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.notfound.UniEatResourceExpiryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.foorun.unieat.util.RegexUtil.REGEX_EMAIL;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {
    private final MemberRepository memberRepository;
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;
    private final PigeonClient pigeonClient;

    /**
     * 회원가입 프로세스 - 이메일 중복 확인
     * @return 중복인가 ?
     */
    @Transactional(readOnly = true)
    public boolean isDuplicateEmail(String email) {
        validateEmail(email);
        return memberRepository.existsByEmail(email);
    }

    /**
     * 회원가입 프로세스 - 이메일 인증 메일 발송
     */
    public void sendVerificationEmail(String email) {
        final String verificationCode = "111111";
        PigeonResponse<Void> pigeonResponse = pigeonClient
                .send(PigeonRequest.formSingleEmailOf(verificationCode, email));

        if (pigeonResponse.getCode() != HttpStatus.OK.value()) {
            throw new UniEatLogicalException();
        }
        emailVerificationCodeRepository.save(EmailVerificationCodeJpo.of(email, verificationCode));
    }

    /**
     * 회원가입 프로세스 - 이메일 인증 코드 확인
     * @param verificationCode 사용자가 입력한 이메일 인증 코드
     */
    public boolean verifyEmail(String email, String verificationCode) {
        EmailVerificationCodeJpo emailVerificationCodeJpo = emailVerificationCodeRepository
                .findById(email)
                .orElseThrow(UniEatResourceExpiryException::new);

        return verificationCode.equals(emailVerificationCodeJpo.getCode());
    }

    /**
     * 회원가입 프로세스 - 닉네임 중복 확인
     * @return 중복인가 ?
     */
    @Transactional(readOnly = true)
    public boolean isDuplicateNickname(String nickname) {
        validateNickname(nickname);
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional
    @Validation
    public void signUp(MemberSignUp memberSignUp) {
//        memberRepository.save(memberSignUp);
    }

    /**
     * 이메일 정규식 검증
     */
    private void validateEmail(String email) {
        if (!REGEX_EMAIL.matcher(email).matches()) {
            throw new UniEatBadRequestException();
        }
    }

    /**
     * 닉네임 정규식 검증
     */
    private void validateNickname(String nickname) {
        if (nickname.length() > 10) {
            throw new UniEatBadRequestException();
        }
    }
}
