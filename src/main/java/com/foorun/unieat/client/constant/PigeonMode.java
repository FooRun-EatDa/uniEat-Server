package com.foorun.unieat.client.constant;

import java.util.Locale;

public enum PigeonMode {
    SINGLE,
    MULTI;

    public final String getLowerCase() {
        return name().toLowerCase(Locale.ROOT);
    }
}
