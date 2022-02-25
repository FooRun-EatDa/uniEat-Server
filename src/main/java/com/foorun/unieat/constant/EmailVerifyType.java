package com.foorun.unieat.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailVerifyType {
    SIGN_UP("email/sign-up-verification"),
    RESET_PASSWORD("email/reset-password-verification");

    private final String templateName;
}
