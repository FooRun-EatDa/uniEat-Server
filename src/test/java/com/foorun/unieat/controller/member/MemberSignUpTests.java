package com.foorun.unieat.controller.member;

import com.foorun.unieat.controller.ControllerTest;
import com.foorun.unieat.service.member.MemberSignUpService;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class MemberSignUpTests extends ControllerTest {
    @MockBean
    protected MemberSignUpService memberSignUpService;
}
