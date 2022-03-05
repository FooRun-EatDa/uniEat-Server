package com.foorun.unieat.service.member;

import com.foorun.unieat.annotation.Validation;
import com.foorun.unieat.domain.member.dto.MemberSignUp;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import com.foorun.unieat.domain.school.repository.SchoolRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.foorun.unieat.util.RegexUtil.REGEX_EMAIL;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {
    private final MemberRepository memberRepository;
    private final SchoolRepository schoolRepository;

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
     * 회원가입 프로세스 - 닉네임 중복 확인
     * @return 중복인가 ?
     */
    @Transactional(readOnly = true)
    public boolean isDuplicateNickname(String nickname) {
        validateNickname(nickname);
        return memberRepository.existsByNickname(nickname);
    }

    /**
     * 회원가입 프로세스 - 회원 생성
     */
    @Validation
    @Transactional
    public void signUp(MemberSignUp memberSignUp) {
        MemberJpo memberJpo = memberSignUp.asJpo();
        SchoolJpo schoolJpo = schoolRepository.findById(memberSignUp.getSchoolId())
                        .orElseThrow(UniEatNotFoundException::new);
        memberJpo.setSchool(schoolJpo);
        memberRepository.save(memberJpo);
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
