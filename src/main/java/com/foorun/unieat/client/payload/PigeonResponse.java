package com.foorun.unieat.client.payload;

import com.foorun.unieat.client.constant.PigeonMethod;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.common.api.ApiResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PigeonResponse<T> extends ApiResponse<T> implements JsonSerializable {
    private PigeonMethod method;
}
