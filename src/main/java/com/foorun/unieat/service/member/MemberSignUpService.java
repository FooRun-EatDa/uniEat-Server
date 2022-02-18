package com.foorun.unieat.service.member;

import com.foorun.unieat.domain.member.dto.MemberSignUp;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.foorun.unieat.util.RegexUtil.REGEX_EMAIL;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입 프로세스 - 이메일 중복 확인
     * @return 중복인가 ?
     */
    @Transactional(readOnly = true)
    public boolean isDuplicateEmail(String email) {
        validateEmail(email);
        return !memberRepository.existsByEmail(email);
    }

    /**
     * 회원가입 프로세스 - 이메일 인증 메일 발송
     */
    public void sendVerificationEmail(String email) {

    }

    /**
     * 회원가입 프로세스 - 이메일 인증 코드 확인
     * @param verificationCode 사용자가 입력한 이메일 인증 코드
     */
    public boolean verifyEmail(String email, String verificationCode) {
        return true;
    }

    /**
     * 회원가입 프로세스 - 닉네임 중복 확인
     * @return 중복인가 ?
     */
    @Transactional(readOnly = true)
    public boolean isDuplicateNickname(String nickname) {
        validateNickname(nickname);
        return !memberRepository.existsByNickname(nickname);
    }

    @Transactional
    public void signUp(MemberSignUp memberSignUp) {
        //  TODO : VALIDATION Member
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
