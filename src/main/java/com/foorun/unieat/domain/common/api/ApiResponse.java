package com.foorun.unieat.domain.common.api;

import com.foorun.unieat.constant.ResponseCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> valueOf(T data) {
        return of(ResponseCode.CODE_200, data);
    }

    public static <T> ApiResponse<T> execute(Runnable runnable) {
        runnable.run();
        return of(ResponseCode.CODE_200, null);
    }

    private static <T> ApiResponse<T> of(ResponseCode responseCode, T data) {
        return new ApiResponse<>(responseCode.getCode(), responseCode.getMessage(), data);
    }
}
