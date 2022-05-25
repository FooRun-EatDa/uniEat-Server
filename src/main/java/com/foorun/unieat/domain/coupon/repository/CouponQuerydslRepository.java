package com.foorun.unieat.domain.coupon.repository;

import com.foorun.unieat.domain.coupon.entity.QCouponJpo;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foorun.unieat.domain.coupon.entity.QCouponJpo.couponJpo;

@Repository
@RequiredArgsConstructor
public class CouponQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public boolean existByEventIdAndMemberId(Long eventId , Long memberId){
      return jpaQueryFactory.selectFrom(couponJpo)
              .where(couponJpo.event.id.eq(eventId)
              .and(couponJpo.member.id.eq(memberId)))
              .fetchFirst() != null;
    }
}
