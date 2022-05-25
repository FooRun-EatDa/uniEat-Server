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
public class CouponJpo extends BaseTimeJpo {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private MemberJpo member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "event")
    private EventJpo event;

}
