package com.foorun.unieat.domain.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {

    private Long to_member; //신고 대상

    private String reported_by; // 어디에서 신고된건지, 리뷰, 쪽지 ..etc

    private String content; //신고 사유 상세

}
