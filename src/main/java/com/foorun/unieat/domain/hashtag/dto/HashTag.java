package com.foorun.unieat.domain.hashtag.dto;


import com.foorun.unieat.domain.hashtag.jpo.HashTagJpo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HashTag {
    private Long id;
    private String content;

    private List<HashTagRestaurant> hashTagRestaurants;

    public static HashTag of(HashTagJpo hashTagJpo) {
        return HashTag.builder()
                .id(hashTagJpo.getId())
                .content(hashTagJpo.getContent())
                .build();
    }
}
