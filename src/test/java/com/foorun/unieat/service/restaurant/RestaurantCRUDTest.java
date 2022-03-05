package com.foorun.unieat.service.restaurant;

import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.given;

public class RestaurantCRUDTest extends ServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @DisplayName("레스토랑 목록 조회")
    @Test
    void getRestSimpleList(){


    }

}
