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

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> valueOf(T data) {
        return of(ResponseCode.CODE_200, data);
    }

    public static <T> ApiResponse<T> success() {
        return of(ResponseCode.CODE_200, null);
    }

    public static <T> ApiResponse<T> of(ResponseCode responseCode) {
        return of(responseCode, null);
    }

    public static <T> ApiResponse<T> of(ResponseCode responseCode, T data) {
        return of(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static ApiResponse<Void> error(ResponseCode responseCode) {
        return of(responseCode);
    }

    public static ApiResponse<Void> badRequest(String message) {
        return of(ResponseCode.CODE_400.getCode(), message, null);
    }
}
