package com.foorun.unieat.service.review;

import com.foorun.unieat.domain.member.dto.Member;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.review.dto.Review;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("리뷰 조회 테스트")
public class ReviewReadTest extends ServiceTest {


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

    @DisplayName("리뷰 목록 조회 테스트")
    @Test
    void showReviewListTest(){




    }
}
