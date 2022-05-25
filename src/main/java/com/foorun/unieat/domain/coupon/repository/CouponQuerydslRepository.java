package com.foorun.unieat.domain.coupon.repository;

import com.foorun.unieat.domain.QuerydslSelectMulti;
import com.foorun.unieat.domain.QuerydslSelectSingle;
import com.foorun.unieat.domain.coupon.entity.CouponJpo;
import com.foorun.unieat.domain.coupon.entity.QCouponJpo;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.foorun.unieat.domain.coupon.entity.QCouponJpo.couponJpo;
import static com.foorun.unieat.domain.event.jpo.QEventJpo.eventJpo;

@Repository
@RequiredArgsConstructor
public class CouponQuerydslRepository implements  QuerydslSelectSingle<CouponJpo,Long> {

    private final JPAQueryFactory jpaQueryFactory;

    public boolean existByEventIdAndMemberId(Long eventId , Long memberId){
      return jpaQueryFactory.selectFrom(couponJpo)
              .where(couponJpo.event.id.eq(eventId)
              .and(couponJpo.member.id.eq(memberId)))
              .fetchFirst() != null;
    }


    public Optional<CouponJpo> findCouponByEventIdAndMemberId(Long eventId, Long memberId){
        return Optional.ofNullable(jpaQueryFactory.selectFrom(couponJpo)
                .where(couponJpo.event.id.eq(eventId)
                .and(couponJpo.member.id.eq(memberId)))
                .fetchFirst());

    }


    @Override
    public Optional<CouponJpo> find(Long couponId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(couponJpo)
                .where(eventJpo.id.eq(couponId))
                .fetchOne());
    }

}
