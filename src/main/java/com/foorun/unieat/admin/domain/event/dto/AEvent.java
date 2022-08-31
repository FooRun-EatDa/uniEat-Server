package com.foorun.unieat.admin.domain.event.dto;


import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.event.EventStatus;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AEvent implements JsonSerializable {
    private Long id;
    private String name;
    private String imgUrl;
    private Long couponCount;
    private String expiredDate;
    private String desc;
    private EventStatus status;
    private List<String> notice;
    private ARestaurant restaurant;

    public static AEvent of(EventJpo eventJpo) {
        AEvent event = new AEvent();
        BeanUtils.copyProperties(eventJpo, event);
        event.restaurant = ARestaurant.of(eventJpo.getRestaurant());
        return event;
    }

    public EventJpo asJpo() {
        return EventJpo.builder()
                .id(id)
                .name(name)
                .imgUrl(imgUrl)
                .couponCount(couponCount)
                .expiredDate(expiredDate)
                .desc(desc)
                .status(status)
                .restaurant(restaurant.asJpo())
                .build();
    }

    public void applyRevision(EventJpo eventJpo) {
        BeanUtils.copyProperties(this, eventJpo);
        if (id == 0L) {
            eventJpo.setId(IdentifyGenerator.number());
        }
        eventJpo.setUpdatedAt(LocalDateTime.now());
    }
}
