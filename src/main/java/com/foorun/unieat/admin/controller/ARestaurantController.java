package com.foorun.unieat.admin.controller;

import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.admin.domain.ARestaurantBusinessHour;
import com.foorun.unieat.admin.domain.ARestaurantHashTag;
import com.foorun.unieat.admin.service.*;
import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.domain.food.dto.Food;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME_REFRESH_TOKEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ARestaurantController.MAPPING_URI, produces = APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*", exposedHeaders = {JwtConstant.HEADER_NAME, HEADER_NAME_REFRESH_TOKEN, HttpHeaders.LOCATION})
@RequiredArgsConstructor
@Api(hidden = true)
public class ARestaurantController {
    public final static String MAPPING_URI = "/admin/restaurant";
    private final ARestaurantService restaurantService;
    private final ARestaurantListService restaurantListService;
    private final ARestaurantFoodService restaurantFoodService;
    private final ARestaurantFileService restaurantImageService;
    private final ARestaurantBusinessHourService restaurantBusinessHourService;
    private final ARestaurantHashTagService restaurantHashTagService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ARestaurant>>> get(
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantListService.fetch(paging)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<ARestaurant>>> search(
            @RequestParam(value = "keyword", required = false) String keyword,
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantListService.search(keyword, paging)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ARestaurant>> get(
            @PathVariable("id") long id) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetch(id)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> post(
            @RequestBody ARestaurant restaurant) {
        long id = restaurantService.save(restaurant);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, id)))
                .body(ApiResponse.success());
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> put(
            @RequestBody ARestaurant restaurant) {
        long id = restaurantService.update(restaurant);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, id)))
                .body(ApiResponse.success());
    }

    @GetMapping(value = "/{restaurantId}/foods")
    public ResponseEntity<ApiResponse<List<Food>>> getFoods(
            @PathVariable("restaurantId") long restaurantId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantFoodService.fetch(restaurantId)));
    }

    @PutMapping(value = "/{restaurantId}/foods", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> putFoods(
            @PathVariable("restaurantId") long restaurantId,
            @RequestBody List<Food> foods) {
        restaurantFoodService.save(restaurantId, foods);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, restaurantId)))
                .body(ApiResponse.success());
    }

    @GetMapping(value = "/{restaurantId}/files")
    public ResponseEntity<ApiResponse<List<FileDetail>>> getFiles(
            @PathVariable("restaurantId") long restaurantId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantImageService.fetch(restaurantId)));
    }

    @PutMapping(value = "/{restaurantId}/files", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> putFiles(
            @PathVariable("restaurantId") long restaurantId,
            @RequestBody List<FileDetail> files) {
        restaurantImageService.save(restaurantId, files);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, restaurantId)))
                .body(ApiResponse.success());
    }

    @GetMapping(value = "/{restaurantId}/business-hours")
    public ResponseEntity<ApiResponse<List<ARestaurantBusinessHour>>> getBusinessHours(
            @PathVariable("restaurantId") long restaurantId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantBusinessHourService.fetch(restaurantId)));
    }

    @PutMapping(value = "/{restaurantId}/business-hours", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> putBusinessHours(
            @PathVariable("restaurantId") long restaurantId,
            @RequestBody List<Food> foods) {
        restaurantFoodService.save(restaurantId, foods);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, restaurantId)))
                .body(ApiResponse.success());
    }

    @GetMapping(value = "/{restaurantId}/hash-tag")
    public ResponseEntity<ApiResponse<List<ARestaurantHashTag>>> getHashTag(
            @PathVariable("restaurantId") long restaurantId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantHashTagService.fetch(restaurantId)));
    }

    @PutMapping(value = "/{restaurantId}/hash-tag", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> putHashTag(
            @PathVariable("restaurantId") long restaurantId,
            @RequestBody List<Food> foods) {
        restaurantFoodService.save(restaurantId, foods);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, restaurantId)))
                .body(ApiResponse.success());
    }
}
