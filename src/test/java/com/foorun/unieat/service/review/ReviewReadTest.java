package com.foorun.unieat.service.review;

import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.review.dto.Review;
import com.foorun.unieat.domain.review.repository.ReviewQueryDslRepository;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.foorun.unieat.constant.ServiceConstant.PAGING_SIZE;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("리뷰 조회 테스트")
public class ReviewReadTest extends ServiceTest {


    @Mock
    private ReviewQueryDslRepository reviewQueryDslRepository;

    private Member member = Member.builder()
            .id(1234567890L)
            .email("testEmail")
            .nickname("dre")
            .gender(0).build();

    private Restaurant restaurant = Restaurant.builder()
            .id(1234567890L)
            .content("맛집 음식점")
            .name("맛집 식당")
            .build();


    private Review review  = Review.builder().id(1L).content("리뷰 내용").starScore(2).member(member)
            .restaurant(restaurant)
            .build();


    /**
     * PAGING_SIZE 만큼의 페이징으로 리뷰 목록 조회
     */
    @DisplayName("리뷰 목록 조회 테스트")
    @Test
    void Given_Paging_EXPECT_Review_List(){
        //given
        Pageable page = PageRequest.of(0,PAGING_SIZE);

        //when
        List<Review> reviews = reviewService.getReviewList(page);

        //then
        verify(reviewQueryDslRepository,times(1)).find(page);

    }
}
