package com.foorun.unieat.domain.feeling.repository;

import com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foorun.unieat.domain.bookmark.jpo.QBookmarkJpo.bookmarkJpo;
import static com.foorun.unieat.domain.feeling.jpo.QReviewFeelingJpo.reviewFeelingJpo;

@Repository
@RequiredArgsConstructor
public class ReviewFeelingQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public boolean isLikedByMember(Long reviewId, Long memberId){
        return jpaQueryFactory.selectFrom(reviewFeelingJpo).where(
                reviewFeelingJpo.review.id.eq(reviewId).and(reviewFeelingJpo.member.id.eq(memberId))
        ).fetchFirst() != null;
    }

}
