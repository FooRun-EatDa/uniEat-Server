package com.foorun.unieat.domain.review.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "review")
public class ReviewJpo extends BaseTimeJpo {
    @Id
    @Column(name="review_id")
    private Long id;

    private String content;

    private String imgUrl;

    @Column(name="star_score")
    private int starScore;

    private String status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @ToString.Exclude
    private RestaurantJpo restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @ToString.Exclude
    private MemberJpo member;


    @OneToMany(mappedBy = "review")
    @ToString.Exclude
    private Set<ReviewFeelingJpo> reviewFeelings = new HashSet<>();




}
