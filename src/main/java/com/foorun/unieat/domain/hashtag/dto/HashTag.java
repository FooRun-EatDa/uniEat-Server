package com.foorun.unieat.domain.hashtag.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTag {
    private Long id;
    private String content;

    private List<HashTagRestaurant> hashTagRestaurants;
}
