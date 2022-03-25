package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;

import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.member.dto.MemberLocation;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.search.dto.SearchLog;
import com.foorun.unieat.service.restaurant.RestaurantBookmarkingService;
import com.foorun.unieat.service.restaurant.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final RestaurantBookmarkingService restaurantBookmarkingService;
    /**
     * 추천 식당 (간략)정보 리스트 형식 조회
     */
    @ApiOperation(value = SwaggerApiInfo.GET_STORE_SIMPLE, notes = "랜딩페이지에서 보이는 추천 식당 정보들 10개씩 페이징 하여 전달")
    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurantSimple>>> getSimpleRestaurant(@RequestParam(name="page") int page){

        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetch(new Paging(page, PAGING_SIZE)))
        );

    }

    /**
     * idx를 이용해 특정 식당 상세 정보 조회
     */
    @ApiOperation(value = SwaggerApiInfo.GET_STORE_DETAIL, notes="특정 식당의 idx를 이용해 특정 식당 상세정보 조회")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<Restaurant>> getRestaurant(@PathVariable(name="id") Long idx){
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetch(idx))
        );
    }


    /**
     * 검색을 이용한 식당 리스트 조회
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "검색어", type ="String"),
            @ApiImplicitParam(name = "lastPage",value = "페이지 수",type = "int")
    })
    @ApiOperation(value = SwaggerApiInfo.GET_STORE_BY_SEARCH, notes = "검색 키워드를 통한 식당 리스트 조회")
    @GetMapping(value = "/search")
    public ResponseEntity<ApiResponse<List<RestaurantSimple>>> getRestaurantByKeyWord(
            @RequestParam(name="keyword") String keyword,
            @RequestParam(name="lastPage") int page) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(
                        restaurantService.fetchBySearching(keyword,new Paging(page, PAGING_SIZE)))
        );
    }

    @ApiImplicitParam(name = "id", value = "유저 id", type="Long")
    @GetMapping(value = "/searchLog")
    public ResponseEntity<ApiResponse<List<SearchLog>>> getSearchLog(
            @RequestParam(name="id")Long id) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(
                        restaurantService.fetchSearchLog(id)
                )
        );
    }


    /**
     * 주변 맛집
     */
    @ApiOperation(value =  SwaggerApiInfo.GET_NEAREST_STORE, notes = "사용자 현재 위치에 따른 주변 맛집 검색")
    @GetMapping(value = "/near")
    public ResponseEntity<ApiResponse<List<RestaurantSimple>>> getNearestRestaurant(@ModelAttribute MemberLocation memberLocation)
    {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetchNearest(memberLocation))
        );
    }


    /**
     *  식당 북마크 (좋아요)
     */

    @ApiOperation(value = SwaggerApiInfo.GET_BOOKMARKING, notes = "식당에 좋아요를 클릭하면 즐겨찾기에 등록됨, 유저가 좋아요한 식당 리스트에서 보여짐")
    @GetMapping(value = "/bookmark/{restaurantId}")
    public ResponseEntity<ApiResponse<Void>> bookmarkingRestaurant(@AuthenticationPrincipal MemberUserDetails userDetails, @PathVariable(name="restaurantId") int storeIdx){
        restaurantBookmarkingService.bookmarking(storeIdx,userDetails);
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }




}
