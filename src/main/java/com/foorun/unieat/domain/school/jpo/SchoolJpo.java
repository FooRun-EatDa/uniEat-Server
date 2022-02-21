package com.foorun.unieat.domain.school.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "school")
public class SchoolJpo extends BaseTimeJpo {
    /**
     * 학교 고유 ID
     */
    @Id
    @Column(name = "school_id")
    private Long id;

    /**
     * 지역 고유 ID
     */
    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionCodeJpo region;

    /**
     * 학교 이름
     */
    @Column
    private String name;

    /**
     * 상태
     */
    @Column
    private String status;
}
