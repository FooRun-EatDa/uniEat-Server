package com.foorun.unieat.service.review;


import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.review.dto.ReviewAddReq;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.domain.review.repository.ReviewRepository;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewWriteTest extends ServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MemberUserDetails memberUserDetails = MemberUserDetails.builder()
            .id(2L)
            .email("hyun123@naver.com")
            .role(Role.USER)
            .nickname("user")
            .build();


    @DisplayName("리뷰 등록")
    @Test
    void reviewWriteTest(){
        //given
        ReviewAddReq reviewAddReq = ReviewAddReq.builder()
                .content("정말 맛있어요!")
                .imgUrl("http://s3.aws.cloud.img/dksdkgkg.jpg")
                .restaurantId(11L)
                .starScore(3)
                .build();

        when(reviewRepository.save(any(ReviewJpo.class))).then(invocation -> {
            System.out.println(invocation);
            ReviewJpo review = invocation.getArgument(0, ReviewJpo.class);
            review.setId(1L);
            return review;
        });
        when(restaurantRepository.findById(reviewAddReq.getRestaurantId()))
                .thenReturn(Optional.of(mock(RestaurantJpo.class)));


        //when
        Long savedStoreId = reviewService.addReview(memberUserDetails, reviewAddReq);

        //then
        assertEquals(savedStoreId, 1L);
        verify(reviewRepository).save(any(ReviewJpo.class));

    }

}
