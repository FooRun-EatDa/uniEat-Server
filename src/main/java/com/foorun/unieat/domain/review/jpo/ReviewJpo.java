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
@Table(name = "review")
public class ReviewJpo extends BaseTimeJpo {
    @Id
    @Column(name="review_id")
    private Long id;

    private String content;

    private int star_score;

    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @ToString.Exclude
    private RestaurantJpo restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @ToString.Exclude
    private MemberJpo memberJpo;


    @OneToMany(mappedBy = "review")
    @ToString.Exclude
    private Set<ReviewFeelingJpo> reviewFeeling = new HashSet<>();




}
