package com.foorun.unieat.domain.school.dto;

import com.foorun.unieat.domain.code.region.dto.RegionCode;
import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class School {
    private Long id;
    private RegionCode region;
    private String name;

    /**
     * static factory method
     */
    public static School of(SchoolJpo schoolJpo) {
        return School.builder()
                .id(schoolJpo.getId())
                .name(schoolJpo.getName())
                .region(RegionCode.of(schoolJpo.getRegion()))
                .build();
    }
}
