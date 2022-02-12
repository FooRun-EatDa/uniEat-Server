package com.foorun.unieat.domain.school.dto;

import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class School {
    private Long id;
    private RegionCodeJpo region;
    private String name;
    private String status;
}
