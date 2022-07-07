package com.foorun.unieat.service.review;


import com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo;
import com.foorun.unieat.domain.feeling.repository.ReviewFeelingQuerydslRepository;
import com.foorun.unieat.domain.feeling.repository.ReviewFeelingRepository;
import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.review.StarScore;
import com.foorun.unieat.domain.review.StarScoreCount;
import com.foorun.unieat.domain.review.dto.RestaurantReviews;
import com.foorun.unieat.domain.review.dto.Review;
import com.foorun.unieat.domain.review.dto.ReviewReq;
import com.foorun.unieat.domain.review.dto.ReviewUpdateReq;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.foorun.unieat.domain.review.repository.ReviewQueryDslRepository;
import com.foorun.unieat.domain.review.repository.ReviewRepository;
import com.foorun.unieat.exception.*;

import com.foorun.unieat.util.IdentifyGenerator;
import com.foorun.unieat.component.LikedCheckComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ReviewService  {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final ReviewQueryDslRepository reviewQueryDslRepository;
    private final ReviewFeelingRepository reviewFeelingRepository;
    private final ReviewFeelingQuerydslRepository reviewFeelingQuerydslRepository;

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

    //점수 유효성 체크
    public boolean starScoreInvalidCheck(ReviewReq reviewAddReq){
        if(0L <= reviewAddReq.getStarScore().getScore() && reviewAddReq.getStarScore().getScore() <= 2L) return true;
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


    //리뷰 리스트 조회(리뷰 피드), 비회원도 가능,
    @Transactional(readOnly = true)
    public List<Review> getReviewList(Pageable pageable){
        MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails == null){
            return reviewQueryDslRepository.find(pageable)
                    .stream()
                    .map(Review::of)
                    .collect(Collectors.toList());
        }

        return reviewQueryDslRepository.find(pageable)
                .stream()
                .map(Review::of)
                .map(r-> addIsLikedValue(r,userDetails))
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
    public Review updateReview(ReviewUpdateReq updateReq, MemberUserDetails memberUserDetails){
        ReviewJpo reviewJpo = reviewQueryDslRepository.find(updateReq.getId()).orElseThrow(UniEatNotFoundException::new);
        if(!updateValidCheck(reviewJpo,memberUserDetails)) throw new UniEatForbiddenException();
        updateReviewJpo(reviewJpo,updateReq);
        return Review.of(reviewJpo); // 업데이트된 리뷰 그대로 리턴
    }

    private void updateReviewJpo(ReviewJpo reviewJpo, ReviewUpdateReq updateReq){

        reviewJpo.setContent(Optional.of(updateReq.getContent()).orElse(reviewJpo.getContent()));
        reviewJpo.setImgUrl(Optional.of(updateReq.getImgUrl()).orElse(reviewJpo.getImgUrl()));
        reviewJpo.setStarScore(Optional.of(updateReq.getStarScore()).orElse(reviewJpo.getStarScore()));

    }


    //리뷰 업데이트 체크
    public boolean updateValidCheck(ReviewJpo reviewJpo, MemberUserDetails member){
        Long writerId = reviewJpo.getMember().getId();
        Long memberId = member.getId();
        if(writerId != memberId)return false;
        else return true;
    }


    //리뷰 좋아요
    public void reviewLiking(Long reviewId, MemberUserDetails memberUserDetails){
        Long memberId = memberUserDetails.getId();
        ReviewFeelingJpo reviewFeelingJpo = ReviewFeelingJpo.builder()
                .id(IdentifyGenerator.number())
                .review(reviewRepository.findById(reviewId).orElseThrow(UniEatNotFoundException::new))
                .member(memberRepository.findById(memberId).orElseThrow(UniEatNotFoundException::new))
                .build();

        reviewFeelingRepository.save(reviewFeelingJpo);
    }



    //리뷰 좋아요 취소
    public void reivewLikingCancel(Long reviewId, MemberUserDetails memberUserDetails){
        Long memberId = memberUserDetails.getId();
        ReviewFeelingJpo reviewFeelingJpo = reviewFeelingRepository.findReviewFeelingJpoByReviewIdAndMemberId(reviewId,memberId).orElseThrow(UniEatBadRequestException::new);
        reviewFeelingRepository.delete(reviewFeelingJpo);
    }

    //특정 식당에 대한 리뷰 조회
    public RestaurantReviews getReviewByRestaurant(Long storeIdx){
        MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //비회원 조회
        if(userDetails == null){
            List<Review> reviews = reviewQueryDslRepository.findByRestaurantId(storeIdx)
                    .stream()
                    .map(Review::of)
                    .collect(Collectors.toList());

            return assembleRestaurantReviewsByReviewsList(reviews);

        }

        List<Review> reviews = reviewQueryDslRepository.findByRestaurantId(storeIdx)
                .stream()
                .map(Review::of)
                .map(r-> addIsLikedValue(r,userDetails))
                .collect(Collectors.toList());
        return assembleRestaurantReviewsByReviewsList(reviews);

    }

    private RestaurantReviews assembleRestaurantReviewsByReviewsList(List<Review> reviews){
        RestaurantReviews restaurantReviews = RestaurantReviews.builder()
                .reviews(reviews)
                .build();

        StarScoreCount counts = new StarScoreCount();

        for (Review review : reviews) {
            StarScore s = Arrays.stream(StarScore.values())
                    .filter(r-> r.equals(review.getStarScore())).findFirst()
                    .orElseThrow(UniEatLogicalException::new);
            String verdictName = s.getName();
            counts.put(verdictName,counts.get(verdictName) + 1); //해당 평가 카운트 증가
        }

        restaurantReviews.setScoreCount(counts.getStarCount());
        return restaurantReviews;


    }


    private Review addIsLikedValue (Review review, MemberUserDetails
            memberUserDetails) {
        //좋아요한 식당이라면
        if (reviewFeelingQuerydslRepository.isLikedByMember(review.getId(), memberUserDetails.getId())) {
            review.setLiked(true);
        } else review.setLiked(false);

        return review;

    }








}
