<<<<<<< HEAD
<<<<<<< HEAD
//package com.foorun.unieat.service.review;
//
//import com.foorun.unieat.domain.member.dto.Member;
//import com.foorun.unieat.domain.restaurant.dto.Restaurant;
//import com.foorun.unieat.domain.review.dto.Review;
//import com.foorun.unieat.domain.review.dto.ReviewReq;
//import com.foorun.unieat.domain.review.jpo.ReviewJpo;
//import com.foorun.unieat.domain.review.repository.ReviewQueryDslRepository;
//import com.foorun.unieat.service.ServiceTest;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.foorun.unieat.constant.ServiceConstant.PAGING_SIZE;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@DisplayName("리뷰 조회 테스트")
//public class ReviewReadTest extends ServiceTest {
//
//
//    private static MockedStatic<Review> reviewMockedStatic;
//
//    @Mock
//    private ReviewQueryDslRepository reviewQueryDslRepository;
//
//    @InjectMocks
//    private ReviewService reviewService;
//
//
//    private static Member member;
//
//    private static Restaurant restaurant;
//
//    private static Review review;
//
//    private static ReviewJpo reviewJpo;
//
//    private static final Long reviewId = 1L;
//
//    @BeforeAll
//    static void setUp(){
//        member = Member.builder()
//                .id(1234567890L)
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
//
//        reviewMockedStatic = mockStatic(Review.class);
//
//    }
//
//
//
//
//    /**
//     * PAGING_SIZE 만큼의 페이징으로 리뷰 목록 조회
//     */
//    @DisplayName("리뷰 목록 조회 테스트")
//    @Test
//    void Given_Paging_Then_Review_List(){
//        //given
//        Pageable page = PageRequest.of(0,PAGING_SIZE);
//
//        //when
//        List<Review> reviews = reviewService.getReviewList(page);
//
//        //then
//        verify(reviewQueryDslRepository,times(1)).find(page);
//
//    }
//
//
//    @DisplayName("리뷰 피드 상세 조회 성공 테스트")
//    @Test
//    void Given_reviewId_Then_ReviewDetail(){
//        when(reviewQueryDslRepository.find(any(Long.class))).thenReturn(Optional.ofNullable(reviewJpo));
//
//        when(Review.of(any())).thenReturn(review);
//
//        Review reviewResult = reviewService.getReviewDetail(reviewId);
//        assertEquals(reviewResult,review);
//        verify(reviewQueryDslRepository).find(any(Long.class));
////        assertDoesNotThrow(()-> reviewService.getReviewDetail(reviewId));
//
//    }
//
//
//}
//


