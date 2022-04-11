package com.foorun.unieat.service.review;


import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.review.dto.ReviewAddReq;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.domain.review.repository.ReviewRepository;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReviewService  {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;


    public Long addReview(MemberUserDetails memberUserDetails, ReviewAddReq reviewDto) {
        MemberJpo member = memberRepository.findById(memberUserDetails.getId())
                .orElseThrow(UniEatForbiddenException::new);
        RestaurantJpo restaurant = restaurantRepository.findById(reviewDto.getRestaurantId())
                .orElseThrow(UniEatNotFoundException::new);

        ReviewJpo reviewJpo = reviewDto.asJpo();
        reviewJpo.setRestaurant(restaurant);
        reviewJpo.setMember(member);
        return reviewRepository.save(reviewJpo).getId();
    }
}
