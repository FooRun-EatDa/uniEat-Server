package com.foorun.unieat.domain.code.region.jpo;

import com.foorun.unieat.domain.code.region.jpo.embed.CoordinateJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "region_code")
public class RegionCodeJpo {
    @Id
    @Column(name = "region_id")
    private Long id;

    /**
     * 광역시/특별시/도 코드 --> 2자리 정수
     */
    private int sido;

    /**
     * 시/군/구 코드 --> 3자리 정수
     */
    private int sigungu;

    /**
     * 읍/면/동 코드 --> 5자리 정수
     */
    private int umd;

    /**
     * 행정 구역 명(주소)
     */
    private String name;

    /**
     * 전체 주소
     */
    private String fullName;

    /**
     * 좌표
     */
    @Embedded
    private CoordinateJpo coordinate;



}
