package com.foorun.unieat.controller;

import com.foorun.unieat.constant.ServiceConstant;
import com.foorun.unieat.constant.SwaggerApiInfo;

import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.service.restaurant.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foorun.unieat.constant.ServiceConstant.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/restaurant", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags=SwaggerApiInfo.STORE)
public class RestaurantController {


    private final RestaurantService restaurantService;

    /**
     * 추천 식당 (간략)정보 리스트 형식 조회
     */
    @ApiOperation(value = SwaggerApiInfo.GET_STORE_SIMPLE, notes = "랜딩페이지에서 보이는 추천 식당 정보들 10개씩 페이징 하여 전달")
    @GetMapping(consumes =APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<RestaurantSimple>>> getSimpleRestaurant(@RequestParam(name="lastIdx") int lastIdx){

        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetch(new Paging(lastIdx, PAGING_SIZE)))
        );

    }

    /**
     * idx를 이용해 특정 식당 상세 정보 조회
     */
    @ApiOperation(value = SwaggerApiInfo.GET_STORE_DETAIL, notes="특정 식당의 idx를 이용해 특정 식당 상세정보 조회")
    @GetMapping(consumes =APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Restaurant>> getRestaurant(@RequestParam(name="idx") Long idx){

        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetch(idx))
        );
    }



}
