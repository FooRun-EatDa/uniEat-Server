package com.foorun.unieat.component;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Likable {

    protected Boolean isLiked;

}
