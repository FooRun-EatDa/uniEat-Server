package com.foorun.unieat.service.review;


import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.review.repository.ReviewQueryDslRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatUnAuthorizationException;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;


@DisplayName("리뷰 삭제 테스트")
public class ReviewDeleteTest extends ServiceTest {

    @Mock
    private ReviewQueryDslRepository reviewQueryDslRepository;


    private final Long reviewId = 1L;

    @DisplayName("작성자가 본인 리뷰 삭제 성공")
    @Test
    void Given_MemberInfo_And_ReviewInfo_Expect_Review_is_Removed(){
        //given
        when(reviewQueryDslRepository.isMemberEqaulToReviewWriter(reviewId,memberUserDetails)).thenReturn(true);
        when(memberUserDetails.getRole()).thenReturn(Role.USER);

        //when
        boolean result = reviewService.reviewDelete(reviewId,memberUserDetails);

        verify(reviewRepository,times(1)).deleteById(reviewId);
        Assertions.assertEquals(result,true);
    }

    @DisplayName("비회원인 경우 리뷰 삭제 실패")
    @Test
    void Given_No_MemberInfo_Throws_UnAuthorization_Exception(){
        //비회원은 시큐리티 홀더에 유저 정보 없음
        when(memberUserDetails.getRole()).thenReturn(null);
        Assertions.assertThrows(UniEatUnAuthorizationException.class,()->{
            reviewService.reviewDelete(reviewId,memberUserDetails);
        });
    }

    @DisplayName("작성자가 아니고 어드민도 아닌 경우 리뷰 삭제 실패")
    @Test
    void Given_Not_ReviewWriter_Trying_to_Remove_Review_Throws_Forbidden_Exception(){
        when(memberUserDetails.getRole()).thenReturn(Role.USER);
        when(reviewQueryDslRepository.isMemberEqaulToReviewWriter(reviewId,memberUserDetails)).thenReturn(false);

        Assertions.assertThrows(UniEatForbiddenException.class, ()->{
            reviewService.reviewDelete(reviewId,memberUserDetails);
        });

    }

    @DisplayName("작성자가 아니지만 어드민일 경우 삭제 성공")
    @Test
    void Given_Admin_Trying_to_Remove_Review_Success(){
        when(memberUserDetails.getRole()).thenReturn(Role.ADMIN);
        boolean result = reviewService.reviewDelete(reviewId,memberUserDetails);

        Assertions.assertEquals(result,true);
        verify(reviewRepository,times(1)).deleteById(reviewId);
    }
}
