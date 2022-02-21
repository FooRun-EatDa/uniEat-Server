package com.foorun.unieat.controller.member;

import com.foorun.unieat.controller.ControllerTest;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.service.member.MemberSignUpService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public abstract class MemberSignUpTests extends ControllerTest {
    @InjectMocks
    protected MemberSignUpService memberSignUpService;

    @MockBean
    protected MemberRepository memberRepository;
}
