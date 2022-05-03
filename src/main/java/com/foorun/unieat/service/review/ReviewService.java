package com.foorun.unieat.service.review;


import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.review.dto.Review;
import com.foorun.unieat.domain.review.dto.ReviewReq;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.domain.review.repository.ReviewQueryDslRepository;
import com.foorun.unieat.domain.review.repository.ReviewRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.exception.UniEatForbiddenException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.exception.UniEatUnAuthorizationException;
import com.foorun.unieat.service.review.validChecker.ReviewValidCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ReviewService  {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final ReviewQueryDslRepository reviewQueryDslRepository;
    private final ReviewValidCheck reviewValidCheck;



    //리뷰 작성, 비회원 불가
    @Transactional
    public Long addReview(MemberUserDetails memberUserDetails, ReviewReq reviewDto) {

        if(!starScoreInvalidCheck(reviewDto))throw new UniEatBadRequestException();


        MemberJpo member = memberRepository.findById(memberUserDetails.getId())
                .orElseThrow(UniEatForbiddenException::new);
        RestaurantJpo restaurant = restaurantRepository.findById(reviewDto.getRestaurantId())
                .orElseThrow(UniEatNotFoundException::new);

        ReviewJpo reviewJpo = reviewDto.asJpo();
        reviewJpo.setRestaurant(restaurant);
        reviewJpo.setMember(member);
        return reviewRepository.save(reviewJpo).getId();
    }

    public boolean starScoreInvalidCheck(ReviewReq reviewAddReq){
        if(0 <= reviewAddReq.getStarScore() && reviewAddReq.getStarScore() <= 2) return true;
        else return false;
    }

    //리뷰 삭제
    @Transactional
    public boolean reviewDelete(Long reviewId, MemberUserDetails memberUserDetails){
        //리뷰 작성자 본인 혹은 어드민인 경우에만 삭제가능
        if(!reviewDeleteValid(reviewId,memberUserDetails)) throw new UniEatForbiddenException();
        reviewRepository.deleteById(reviewId);
        return true;
    }

    private boolean reviewDeleteValid(Long reviewId,MemberUserDetails memberUserDetails){
        try{
        if(memberUserDetails.getRole().equals(Role.ADMIN)) return true; //어드민이라면 삭제가능
            //어드민이 아니라면 작성자인지 확인
            return reviewQueryDslRepository.isMemberEqaulToReviewWriter(reviewId,memberUserDetails);
        }catch (NullPointerException e){
            throw new UniEatUnAuthorizationException();
        }
    }


    //리뷰 리스트 조회, 비회원도 가능
    @Transactional(readOnly = true)
    public List<Review> getReviewList(Pageable pageable){
        return reviewQueryDslRepository.find(pageable)
                .stream()
                .map(Review::of)
                .collect(Collectors.toList());
    }

    //리뷰 상세 조회, 비회원도 가능
    @Transactional(readOnly = true)
    public Review getReviewDetail(Long reviewId){
        return Review.of(reviewQueryDslRepository.find(reviewId)
                .orElseThrow(UniEatNotFoundException::new));

    }

    //리뷰 수정
    @Transactional
    public Review updateReview(ReviewReq updateReq, MemberUserDetails memberUserDetails){
        ReviewJpo reviewJpo = reviewQueryDslRepository.find(updateReq.getId()).orElseThrow(UniEatNotFoundException::new);
        if(!reviewValidCheck.updateValidCheck(reviewJpo,memberUserDetails)) throw new UniEatForbiddenException();
        updateReviewJpo(reviewJpo,updateReq);
        return Review.of(reviewJpo); // 업데이트된 리뷰 그대로 리턴
    }

    private void updateReviewJpo(ReviewJpo reviewJpo, ReviewReq updateReq){

        reviewJpo.setContent(Optional.of(updateReq.getContent()).orElse(reviewJpo.getContent()));
        reviewJpo.setImgUrl(Optional.of(updateReq.getImgUrl()).orElse(reviewJpo.getImgUrl()));
        reviewJpo.setStarScore(Optional.of(updateReq.getStarScore()).orElse(reviewJpo.getStarScore()));

    }


    //TODO: 리뷰 신고 기능




//    //리뷰를 수정할 수 있는 권한이 있는지 확인
//    public Boolean updateValidCheck(ReviewJpo reviewJpo, MemberUserDetails member){
//        Long writerId = reviewJpo.getMember().getId();
//        Long memberId = member.getId();
//        if(writerId != memberId)return false;
//        else return true;
//
//    }
//


}
