package com.foorun.unieat.util;

import java.util.regex.Pattern;

public final class RegexUtil {
    public static final Pattern REGEX_EMAIL = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
    public static final Pattern REGEX_PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$");

    /**
     * 사용자 이메일 정규식 검증
     *
     * @param email '사용자' 이메일
     * @return 매치하는가 ?
     */
    public static boolean isMatchMemberEmail(String email) {
        return REGEX_EMAIL.matcher(email).matches();
    }

    /**
     * 사용자 비밀번호 정규식 검증
     *
     * @param password '사용자' 비밀번호
     * @return 매치하는가 ?
     */
    public static boolean isMatchMemberPassword(String password) {
        return REGEX_PASSWORD.matcher(password).matches();
    }
}
