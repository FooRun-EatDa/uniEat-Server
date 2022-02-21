package com.foorun.unieat.client.constant;

import java.util.Locale;

public enum PigeonMethod {
    EMAIL,
    MESSAGE;

    public final String getLowerCase() {
        return name().toLowerCase(Locale.ROOT);
    }
}
