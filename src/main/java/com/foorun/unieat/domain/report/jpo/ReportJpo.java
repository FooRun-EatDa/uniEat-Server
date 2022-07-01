package com.foorun.unieat.domain.report.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.report.ReportType;
import com.foorun.unieat.domain.report.dto.Report;
import com.foorun.unieat.util.ReportDataConverter;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "report")
public class ReportJpo extends BaseTimeJpo {

    @Id
    private Long id;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "reported_by")
    private ReportType reportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="from_member_id")
    @ToString.Exclude
    private MemberJpo fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="to_member_id")
    @ToString.Exclude
    private MemberJpo toMember;


    @Convert(converter = ReportDataConverter.class)
    private Report content;



}
