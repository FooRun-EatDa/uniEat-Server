package com.foorun.unieat.domain.event.dto;


import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Event implements JsonSerializable {

    private Long id;
    //이벤트 테마(이름)
    private String name;

    private String imgUrl;

    //이벤트 대상 식당
    private String restaurantName;

    //이벤트 종료 시간
    private String expiredDate;

    //이벤트 설명
    private String desc;

    //이벤트 유의사항
    private String notice;

    private String status;


    public static Event of(EventJpo eventJpo){
        Event event = new Event();
        BeanUtils.copyProperties(eventJpo,event);
        event.restaurantName = eventJpo.getRestaurant().getName();
        event.status = eventJpo.getStatus().name();
        return event;
    }
}