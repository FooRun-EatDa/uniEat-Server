package com.foorun.unieat.domain.post.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFileIdJpo implements JsonSerializable {
    private Long post;
    private String file;
}
