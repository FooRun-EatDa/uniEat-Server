package com.foorun.unieat.domain.code.region.dto.embed;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.code.region.jpo.embed.CoordinateJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordinate implements JsonSerializable {
    private double latitude;
    private double longitude;

    /**
     * factory method
     * @param coordinateJpo JPA Object
     * @return properties copied instance
     */
    public static Coordinate of(CoordinateJpo coordinateJpo) {
        Coordinate coordinate = new Coordinate();
        BeanUtils.copyProperties(coordinateJpo, coordinate);
        return coordinate;
    }

    public static Coordinate of(double latitude, double longitude) {
        return new Coordinate(latitude, longitude);
    }

    public CoordinateJpo asJpo() {
        return CoordinateJpo.of(latitude, longitude);
    }
}
