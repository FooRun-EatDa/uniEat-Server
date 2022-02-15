package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/restaurant", produces = APPLICATION_JSON_VALUE)
public class RestaurantController {


    /**
     * 식당 (간략)정보 조회
     */
//    @ApiOperation(value = SwaggerApiInfo.GET_STORE_SIMPLE)
//    @GetMapping(value = "/", consumes =APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse<>> getSimpleRestaurant(@RequestParam(name="restaurant_id") int restaurantId){
//
//
//    }

    /**
     * 식당 상세 정보 조회
     */



}
