package com.foorun.unieat.domain.review.repository;

import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.domain.review.jpo.QReviewJpo;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.restaurant.jpo.QRestaurantJpo.restaurantJpo;
import static com.foorun.unieat.domain.review.jpo.QReviewJpo.*;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslRepository implements QuerydslSelectMulti<ReviewJpo>, QuerydslSelectSingle<ReviewJpo,Long> {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewJpo> find(Pageable pageable) {
        return jpaQueryFactory.selectFrom(reviewJpo)
//                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .orderBy(reviewJpo.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Optional<ReviewJpo> find(Long aLong) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(reviewJpo).
                where(reviewJpo.id.eq(aLong))
                .fetchOne());
    }


    //멤버 id로 작성자가 같은지 비교
    public boolean isMemberEqaulToReviewWriter(Long reviewId, MemberUserDetails memberUserDetails){
        ReviewJpo review = jpaQueryFactory.selectFrom(reviewJpo).where(reviewJpo.id.eq(reviewId)).fetchOne();
        if(review.getMember().getId() == memberUserDetails.getId()) return true;
        else return false;

    }



}
