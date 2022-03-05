package com.foorun.unieat.service;

import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.service.restaurant.RestaurantService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantQuerydslRepository restaurantQuerydslRepository;
    @Mock
    private JPAQueryFactory jpaQueryFactory;


}
