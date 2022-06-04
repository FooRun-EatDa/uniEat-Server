package com.foorun.unieat.domain.event.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.event.EventStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EventValidResponse implements JsonSerializable {

    private String content;
    private int status;


}
