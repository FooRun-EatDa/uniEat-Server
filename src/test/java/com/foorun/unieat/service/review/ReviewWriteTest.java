package com.foorun.unieat.service.review;


import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;

import com.foorun.unieat.domain.review.dto.ReviewReq;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.domain.review.repository.ReviewRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewWriteTest extends ServiceTest {



    private ReviewReq reviewAddReq = ReviewReq.builder()
            .content("정말 맛있어요!")
            .imgUrl("http://s3.aws.cloud.img/dksdkgkg.jpg")
            .restaurantId(11L)
            .starScore(2)
            .build();

    @DisplayName("리뷰 등록")
    @Test
    void When_Review_Posting_Expect_SavedReviewId_SameAs_ReqReviewId(){
        //given
        ReviewJpo mock = any(ReviewJpo.class);

        when(reviewRepository.save(mock)).then(invocation -> {
            System.out.println(invocation);
            ReviewJpo review = invocation.getArgument(0, ReviewJpo.class);
            review.setId(1L);
            return review;
        });

        when(memberRepository.findById(memberUserDetails.getId()))
                .thenReturn(Optional.of(mock(MemberJpo.class)));

        when(restaurantRepository.findById(reviewAddReq.getRestaurantId()))
                .thenReturn(Optional.of(mock(RestaurantJpo.class)));

        when(memberRepository.findById(memberUserDetails.getId())).thenReturn(Optional.of(mock(MemberJpo.class)));

        //when
        Long savedStoreId = reviewService.addReview(memberUserDetails, reviewAddReq);

        //then
        assertEquals(savedStoreId, 1L);
        verify(reviewRepository).save(any(ReviewJpo.class));
    }


    @DisplayName("리뷰 등록 실패 : 유효하지 않은 레스토랑")
    @Test
    void Should_Throw_Exception_When_Invalid_Store_Given(){
        //given
        when(memberRepository.findById(memberUserDetails.getId()))
                .thenReturn(Optional.of(mock(MemberJpo.class)));

        assertThrows(UniEatNotFoundException.class,()->{
            reviewService.addReview(memberUserDetails,reviewAddReq);
        });
    }


    @DisplayName("리뷰 등록 실패 : 유효하지 않은 유저")
    @Test
    void Should_Throw_Exception_When_MemberUserDetail_Invalid(){

        assertThrows(UniEatForbiddenException.class,()->{
            reviewService.addReview(memberUserDetails,reviewAddReq);
        });

    }

    @DisplayName("리뷰 등록 실패 : 리뷰 점수 0~2점 이외 일 경우")
    @Test
    void Should_Throw_Exception_When_Score_Invalid(){

        ReviewReq review = ReviewReq.builder().starScore(3).content("등록안되는 리뷰").build();
        ReviewReq review2 = ReviewReq.builder().starScore(-1).content("등록안되는 리뷰").build();

        assertThrows(UniEatBadRequestException.class,()->{
            reviewService.addReview(memberUserDetails,review);
        });

        assertThrows(UniEatBadRequestException.class,()->{
            reviewService.addReview(memberUserDetails,review2);
        });

    }


}
