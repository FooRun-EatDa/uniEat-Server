package com.foorun.unieat.client.payload;

import com.foorun.unieat.client.constant.PigeonMethod;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.common.api.ApiResponse;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PigeonResponse<T> extends ApiResponse<T> implements JsonSerializable {
    private PigeonMethod method;

    /**
     * 성공 여부 확인
     * @return 성공 ?
     */
    public boolean isSuccess() {
        return getCode() != HttpStatus.OK.value();
    }
}
