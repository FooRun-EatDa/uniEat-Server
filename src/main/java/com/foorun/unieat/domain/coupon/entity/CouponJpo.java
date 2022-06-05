package com.foorun.unieat.domain.coupon.entity;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "coupon")
public class CouponJpo  {

    @Id
    @Column(name = "coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpo member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "event_id")
    private EventJpo event;

}
