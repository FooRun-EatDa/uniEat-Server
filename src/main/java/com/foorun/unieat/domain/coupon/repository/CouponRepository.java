package com.foorun.unieat.domain.coupon.repository;

import com.foorun.unieat.domain.coupon.entity.CouponJpo;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponJpo,Long> {


}
