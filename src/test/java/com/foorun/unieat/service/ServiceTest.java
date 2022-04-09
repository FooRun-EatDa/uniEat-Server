package com.foorun.unieat.service;


import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.review.repository.ReviewRepository;
import com.foorun.unieat.service.restaurant.RestaurantService;
import com.foorun.unieat.service.review.ReviewService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest {


    @InjectMocks
    protected ReviewService reviewService;

    @Mock
    protected ReviewRepository reviewRepository;

    @Mock
    protected MemberRepository memberRepository;

    @Mock
    protected RestaurantRepository restaurantRepository;


}
