//package com.foorun.unieat.service.review;
//
//
//import com.foorun.unieat.domain.member.dto.Member;
//import com.foorun.unieat.domain.member.dto.MemberUserDetails;
//import com.foorun.unieat.domain.member.repository.MemberRepository;
//import com.foorun.unieat.domain.restaurant.dto.Restaurant;
//import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
//import com.foorun.unieat.domain.review.dto.Review;
//import com.foorun.unieat.domain.review.dto.ReviewReq;
//import com.foorun.unieat.domain.review.jpo.ReviewJpo;
//import com.foorun.unieat.domain.review.repository.ReviewQueryDslRepository;
//import com.foorun.unieat.domain.review.repository.ReviewRepository;
//import com.foorun.unieat.exception.UniEatForbiddenException;
//import com.foorun.unieat.service.ServiceTest;
//import com.foorun.unieat.service.review.validChecker.ReviewValidCheck;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@DisplayName("리뷰 업데이트 테스트")
//public class ReviewUpdateTest extends ServiceTest {
//
//    private static MockedStatic<Review> reviewMockedStatic;
//
//
//    @Mock
//    private ReviewQueryDslRepository reviewQueryDslRepository;
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @Mock
//    private RestaurantRepository restaurantRepository;
//
//    @Mock
//    private MemberRepository memberRepository;
//
//    @Mock
//    private ReviewValidCheck reviewValidCheck;
//
//    @InjectMocks
//    private ReviewService reviewService;
//
//
//    private static Review review;
//
//    private static ReviewJpo reviewJpo;
//
//    private static final Long reviewId = 1L;
//
//    private static Member member;
//
//    private static Restaurant restaurant;
//
//    @BeforeAll
//    static void setUp(){
//        member = Member.builder()
//                .id(memberId)
//                .email("testEmail")
//                .nickname("dre")
//                .gender(0).build();
//
//        restaurant = Restaurant.builder()
//                .id(1234567890L)
//                .content("맛집 음식점")
//                .name("맛집 식당")
//                .build();
//
//        review  = Review.builder().id(reviewId).content("리뷰 내용").starScore(2).member(member)
//                .restaurant(restaurant)
//                .imgUrl("/images/128364819991929.jpg")
//                .build();
//        reviewJpo = review.asJpo();
//        reviewMockedStatic = mockStatic(Review.class);
//
//    }
//
//
//
//
//    @DisplayName("리뷰 아이디와 멤버 정보를 갖고 수정 성공 테스트")
//    @Test
//    void Given_ReviewUpdate_With_ReviewId_And_MemberDetail_Then_Success(){
//        when(reviewQueryDslRepository.find(any(Long.class))).thenReturn(Optional.ofNullable(reviewJpo));
////        when(reviewService.updateValidCheck(any(),any())).thenReturn(true);
//        ReviewReq reviewUpdateReq = ReviewReq.builder() //수정 요청 dto
//                .id(reviewId)
//                .content("수정할 내용")
//                .restaurantId(reviewId)
//                .imgUrl(review.getImgUrl())
//                .starScore(1)
//                .build();
//
//
//        Review updatedReview = updateReview(reviewUpdateReq); //업데이트 결과 수정된 리뷰
//        when(Review.of(any(ReviewJpo.class))).thenReturn(updatedReview);
//
//        Review updatedReviewResult = reviewService.updateReview(reviewUpdateReq,memberUserDetails);
//        assertEquals(updatedReview,updatedReviewResult);
//
//
//    }
//
//
//    @DisplayName("리뷰 수정 실패, 본인이 쓴 리뷰 아님")
//    @Test
//    void Given_ReviewId_Mismatch_with_memberId_Then_Update_Failed(){
//        //유저 인증 실패
////        when(reviewService.updateValidCheck(any(),any())).thenReturn(false);
//        when(reviewQueryDslRepository.find(any(Long.class))).thenReturn(Optional.ofNullable(reviewJpo));
//        ReviewReq reviewUpdateReq = ReviewReq.builder() //수정 요청 dto
//                .id(reviewId)
//                .content("수정할 내용")
//                .restaurantId(reviewId)
//                .imgUrl(review.getImgUrl())
//                .starScore(1)
//                .build();
//
//
//        assertThrows(UniEatForbiddenException.class,()->{
//            reviewService.updateReview(reviewUpdateReq,memberUserDetails);
//        });
//
//    }
//
//    //ReviewJpo 업데이트하고 해당 Review를 반영하는 함수라고 가정
//    private Review updateReview(ReviewReq updateReq){
//        Review review = new Review();
//        review.setId(reviewId);
//        return updateReq.updateReviewByReq(review);
//    }
//
//
//
//}
