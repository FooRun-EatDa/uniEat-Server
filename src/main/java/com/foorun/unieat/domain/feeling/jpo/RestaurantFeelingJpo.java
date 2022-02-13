package com.foorun.unieat.domain.feeling.jpo;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantFeelingJpo {

    @Id
    @Column(name="restaurant_feeling_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewJpo review;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberJpo member;


}
