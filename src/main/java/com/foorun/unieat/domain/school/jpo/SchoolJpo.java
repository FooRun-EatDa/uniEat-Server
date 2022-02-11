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
    @Id
    @Column(name = "school_id", columnDefinition = "학교 고유 ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "region_id", columnDefinition = "지역 고유 ID")
    private RegionCodeJpo region;

    @Column(columnDefinition = "학교 이름")
    private String name;

    @Column(columnDefinition = "상태")
    private String status;
}
