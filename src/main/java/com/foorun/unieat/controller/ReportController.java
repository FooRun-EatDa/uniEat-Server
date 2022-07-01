package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.report.dto.Report;
import com.foorun.unieat.service.report.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/report", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags= SwaggerApiInfo.REPORT)
public class ReportController {

    private final ReportService reportService;



    @ApiImplicitParam(name = "report_type", required = true,
            value = "리뷰 신고 : 0\n" +
                    "게시물 신고 : 1\n" +
                    "메시지 신고 : 2\n" +
                    "댓글 신고 : 3\n")
    @ApiOperation(value = SwaggerApiInfo.REPORTING,notes = "Body에 담기는 type 은 신고 사유를 말함, 욕설/비방 등..")
    @PostMapping("")
    public ResponseEntity<ApiResponse<Void>> reporting(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestParam(name = "report_type")Long type,
            @RequestBody Report report
    ){
        reportService.reporting(type,report,memberUserDetails);
        return ResponseEntity.ok(ApiResponse.success());

    }


    @ApiOperation(value = SwaggerApiInfo.UNREPORTING,notes = "임의로 신고 누적 유저 정지 해제")
    @PatchMapping("")
    public ResponseEntity<ApiResponse<Void>> unreporting(@RequestParam(name = "memberId") Long memberId){
        reportService.exitBlocked(memberId);
        return ResponseEntity.ok(ApiResponse.success());

    }

}
