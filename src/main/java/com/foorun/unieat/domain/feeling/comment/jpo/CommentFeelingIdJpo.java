package com.foorun.unieat.domain.feeling.comment.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentFeelingIdJpo implements JsonSerializable {
    private Long member;
    private Long comment;
}
