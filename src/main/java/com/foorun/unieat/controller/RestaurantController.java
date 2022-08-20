package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.bookmark.dto.BookmarkingReq;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.member.dto.MemberLocation;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.restaurant.dto.FilteringRestaurant;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.search.dto.SearchLog;
import com.foorun.unieat.service.bookmark.BookmarkService;
import com.foorun.unieat.service.restaurant.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foorun.unieat.constant.ServiceConstant.PAGING_SIZE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/restaurant", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags=SwaggerApiInfo.STORE)
public class RestaurantController {


    private final RestaurantService restaurantService;
    private final BookmarkService bookmarkService;

    /**
     * 추천 식당 (간략)정보 리스트 형식 조회 (주변맛집)
     */
    @ApiOperation(value = SwaggerApiInfo.GET_STORE_SIMPLE, notes = "랜딩페이지에서 보이는 추천 식당 정보들 10개씩 페이징 하여 전달")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Restaurant>>> getSimpleRestaurant(@RequestParam(name="lastPage") int page){
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
    public ResponseEntity<ApiResponse<List<Restaurant>>> getRestaurantByKeyWord(
            @RequestParam(name="keyword") String keyword,
            @RequestParam(name="lastPage") int page) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(
                        restaurantService.fetchBySearching(keyword,new Paging(page, PAGING_SIZE)))
        );
    }


    @ApiImplicitParam(name = "text", value = "삭제할 검색어",type = "String")
    @ApiOperation(value = SwaggerApiInfo.DELETE_SEARCH_LOG,notes = "특정 검색 키워드 삭제")
    @DeleteMapping(value = "/search")
    public ResponseEntity<ApiResponse<Void>> deleteSearchLog(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails,
            @RequestParam(name = "text") String text){
        restaurantService.deleteSearchLog(memberUserDetails.getId(),text);
        return ResponseEntity.ok(
                ApiResponse.success()
        );


    }



    @ApiOperation(value = SwaggerApiInfo.POST_STORE_BY_FILTER, notes = "필터를 이용한 식당 검색")
    @PostMapping(value = "/filter/search")
    public ResponseEntity<ApiResponse<List<Restaurant>>> getRestaurantByFilter(@RequestBody FilteringRestaurant filteringRestaurant,@RequestParam(name = "lastPage")int page)
    {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetchByFiltering(filteringRestaurant,new Paging(page,PAGING_SIZE)))
        );
    }



    @ApiOperation(value = SwaggerApiInfo.GET_SEARCH_LOG, notes = "검색 기록 가져오기")
    @GetMapping(value = "/searchLog")
    public ResponseEntity<ApiResponse<List<SearchLog>>> getSearchLog(
            @AuthenticationPrincipal MemberUserDetails memberUserDetails) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(
                        restaurantService.fetchSearchLog(memberUserDetails.getId())
                )
        );
    }


    /**
     * 주변 맛집
     */
    @ApiOperation(value =  SwaggerApiInfo.GET_NEAREST_STORE, notes = "사용자 현재 위치에 따른 주변 맛집 검색")
    @PostMapping(value = "/near")
    public ResponseEntity<ApiResponse<List<RestaurantSimple>>> getNearestRestaurant(@RequestBody MemberLocation memberLocation, @AuthenticationPrincipal MemberUserDetails memberUserDetails)
    {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetchNearest(memberLocation,memberUserDetails))
        );
    }


    /**
     * 지도에 표시되는 맛집 Top 50
     */
    @ApiOperation(value = SwaggerApiInfo.GET_TOP_STORE_TO_MAP, notes = "지도에 표시되는 맛집 탑")
    @PostMapping(value = "/top/map")
    public ResponseEntity<ApiResponse<List<Restaurant>>> getTopRestaurant(@RequestBody MemberLocation memberLocation,@AuthenticationPrincipal MemberUserDetails memberUserDetails){

        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetchMap(memberLocation,memberUserDetails))
        );
    }


    /**
     * 식당 좋아요하기(북마크)
     */
    @ApiOperation(value = SwaggerApiInfo.POST_BOOKMARKING,notes = "유저가 식당을 좋아요(즐겨찾기)하기")
    @PostMapping(value = "/bookmarkingList")
    public ResponseEntity<ApiResponse<Void>> bookmarkingRestaurant(@AuthenticationPrincipal MemberUserDetails memberUserDetails, @RequestBody BookmarkingReq bookmarkingList){
        bookmarkService.bookmarking(bookmarkingList.getMarkingList(),memberUserDetails);
        return ResponseEntity.ok(
                ApiResponse.success()
        );
    }

    /**
     * 식당 좋아요 취소
     */
    @ApiOperation(value = SwaggerApiInfo.DELETE_BOOKMARKING, notes = "유저가 식당 좋아요 취소하기")
    @DeleteMapping(value = "/bookmarkingList")
    public ResponseEntity<ApiResponse<Void>> bookmarkingCancel(@AuthenticationPrincipal MemberUserDetails memberUserDetails, @RequestBody BookmarkingReq bookmarkingList){
        bookmarkService.bookmarkCancel(bookmarkingList.getMarkingList(),memberUserDetails);
        return ResponseEntity.ok(ApiResponse.success());
    }



    /**
     * 좋아요한 식당 리스트
     */
    @ApiOperation(value = SwaggerApiInfo.GET_BOOMARKEDLIST,notes = "유저가 좋아요(즐겨찾기)한 식당 리스트 가져옴")
    @GetMapping(value = "/bookmark")
    public ResponseEntity<ApiResponse<List<Restaurant>>> getBookmarkedRestaurantList(@AuthenticationPrincipal MemberUserDetails memberUserDetails){
        return ResponseEntity.ok(ApiResponse.valueOf((bookmarkService.getBookmarkedRestaurantList(memberUserDetails))));

    }




}
