package com.foorun.unieat.domain.feeling.post.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFeelingIdJpo implements JsonSerializable {
    private Long post;
    private Long member;
}
