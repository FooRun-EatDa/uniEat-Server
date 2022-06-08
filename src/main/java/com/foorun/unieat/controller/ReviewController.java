package com.foorun.unieat.controller;


import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.review.dto.Review;
import com.foorun.unieat.domain.review.dto.ReviewReq;
import com.foorun.unieat.domain.review.dto.ReviewUpdateReq;
import com.foorun.unieat.service.review.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foorun.unieat.constant.ServiceConstant.PAGING_SIZE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/review", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags= SwaggerApiInfo.Review)
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = SwaggerApiInfo.POST_REVIEW_ADD,notes = "리뷰 작성(회원만 가능), " +
            "starScore 는 0 ~ 2까지 가능, 2 : 꿀맛 , 1 : 엥간 , 0 : 아쉽")
    @PostMapping("")
    public ResponseEntity<ApiResponse<Long>> reviewAdd(@RequestBody ReviewReq reviewReq, @AuthenticationPrincipal MemberUserDetails memberUserDetails){

        return ResponseEntity.ok(
                ApiResponse.valueOf(
                        reviewService.addReview(memberUserDetails,reviewReq)
                )
        );
    }


    @ApiOperation(value = SwaggerApiInfo.DELETE_REVIEW, notes = "리뷰 삭제(본인 혹은 어드민인 경우에만 가능)")
    @DeleteMapping("/{review_id}")
    public ResponseEntity<ApiResponse<Void>> reviewDelete(@PathVariable(name = "review_id") Long reviewId,@AuthenticationPrincipal MemberUserDetails memberUserDetails){
        reviewService.reviewDelete(reviewId,memberUserDetails);
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }


    @ApiOperation(value = SwaggerApiInfo.GET_REVIEW_LIST, notes = "리뷰 피드 조회(페이징 리뷰 조회")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewList(@RequestParam(name="lastPage") int page){

        return ResponseEntity.ok(
                ApiResponse.valueOf(reviewService.getReviewList(new Paging(page,PAGING_SIZE)))
        );
    }

    @ApiOperation(value = SwaggerApiInfo.GET_REVIEW, notes = "id를 통한 특정 리뷰 상세 조회")
    @GetMapping("/{review_id}")
    public ResponseEntity<ApiResponse<Review>> getReviewDetail(@PathVariable(name = "review_id") Long reviewId){
        return ResponseEntity.ok(
                ApiResponse.valueOf(reviewService.getReviewDetail(reviewId))
        );
    }

    @ApiOperation(value = SwaggerApiInfo.PATCH_REVIEW, notes = "특정 리뷰 수정 요청 양식에 따른 리뷰 수정")
    @PatchMapping("/{review_id}")
    public ResponseEntity<ApiResponse<Void>> patchReview(@RequestBody ReviewUpdateReq reviewReq, @AuthenticationPrincipal MemberUserDetails memberUserDetails){
        reviewService.updateReview(reviewReq,memberUserDetails);
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }


    /**
     * 리뷰 좋아요
     */


    /**
     * 리뷰 좋아요 취소
     */



}
