package com.foorun.unieat.util;

import java.util.regex.Pattern;

public final class RegexUtil {
    public static final Pattern REGEX_EMAIL = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
}
