package com.foorun.unieat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.service.restaurant.RestaurantService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest {

    @Mock
    private RestaurantQuerydslRepository restaurantQuerydslRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private JPAQueryFactory jpaQueryFactory;

    @InjectMocks
    private RestaurantService restaurantService;

}
