package com.foorun.unieat.domain.code.region.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.code.region.dto.embed.Coordinate;
import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionCode implements JsonSerializable {
    private Long id;
    private int sido;
    private int sigungu;
    private int umd;
    private String name;
    private String fullName;
    private Coordinate coordinate;

    /**
     * factory method
     * @param regionCodeJpo JPA Object
     * @return properties copied instance
     */
    public static RegionCode of(RegionCodeJpo regionCodeJpo) {
        RegionCode regionCode = new RegionCode();
        BeanUtils.copyProperties(regionCodeJpo, regionCode);
        regionCode.coordinate = Coordinate.of(regionCodeJpo.getCoordinate());
        return regionCode;
    }

    public RegionCodeJpo asJpo() {
        RegionCodeJpo regionCodeJpo = new RegionCodeJpo();
        BeanUtils.copyProperties(this, regionCodeJpo);
        return regionCodeJpo;
    }
}
