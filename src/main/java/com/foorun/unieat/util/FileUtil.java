package com.foorun.unieat.util;

public final class FileUtil {
    private static final String FOORUN_AWS_S3_BASE_URL = "FOORUN_AWS_S3_BASE_URL";

    public static String getAmazonS3BaseURL() {
        return System.getenv(FOORUN_AWS_S3_BASE_URL);
    }
}
