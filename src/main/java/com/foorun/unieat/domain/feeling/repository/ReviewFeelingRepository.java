package com.foorun.unieat.domain.feeling.repository;

import com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewFeelingRepository extends JpaRepository<ReviewFeelingJpo,Long> {

    @Query("SELECT R FROM ReviewFeelingJpo R WHERE R.review.id = :review_id AND R.member.id = :member_id")
    Optional<ReviewFeelingJpo> findReviewFeelingJpoByReviewIdAndMemberId(@Param(value = "review_id") Long reviewId, @Param(value = "member_id")Long memberId);



}
