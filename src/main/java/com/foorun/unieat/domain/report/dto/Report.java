package com.foorun.unieat.domain.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {
    @ApiModelProperty(notes = "신고 대상 멤버 아이디")
    private Long to_member; //신고 대상

    @ApiModelProperty(notes = "어디에서 신고된건지 기입", example = "리뷰, 쪽지 ...etc")
    private String reported_by; // 어디에서 신고된건지, 리뷰, 쪽지 ..etc


    @ApiModelProperty(notes = "신고 사유 상세", example = "정치적인 글을 지속적으로 작성하여 분란을 조장합니다.")
    private String content; //신고 사유 상세

}
