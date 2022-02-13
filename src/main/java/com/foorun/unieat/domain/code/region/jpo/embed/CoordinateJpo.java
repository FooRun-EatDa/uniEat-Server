package com.foorun.unieat.domain.code.region.jpo.embed;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class CoordinateJpo {
    /**
     * 위도
     */
    private double latitude;

    /**
     * 경도
     */
    private double longitude;

    /**
     * static factory method
     */
    public static CoordinateJpo of(double latitude, double longitude) {
        return new CoordinateJpo(latitude, longitude);
    }
}
