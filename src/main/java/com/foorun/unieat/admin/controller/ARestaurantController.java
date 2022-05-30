package com.foorun.unieat.admin.controller;

import com.foorun.unieat.admin.service.ARestaurantFileService;
import com.foorun.unieat.admin.service.ARestaurantFoodService;
import com.foorun.unieat.admin.service.ARestaurantListService;
import com.foorun.unieat.admin.service.ARestaurantService;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.domain.food.dto.Food;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ARestaurantController.MAPPING_URI, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(hidden = true)
public class ARestaurantController {
    public final static String MAPPING_URI = "/admin/restaurant";
    private final ARestaurantService restaurantService;
    private final ARestaurantListService restaurantListService;
    private final ARestaurantFoodService restaurantFoodService;
    private final ARestaurantFileService restaurantImageService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Restaurant>>> get(
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantListService.fetch(paging)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Restaurant>>> search(
            @RequestParam(value = "keyword", required = false) String keyword,
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantListService.search(keyword, paging)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Restaurant>> get(
            @PathVariable("id") long id) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantService.fetch(id)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> post(
            @RequestBody Restaurant restaurant) {
        long id = restaurantService.save(restaurant);
        return ResponseEntity
                .created(URI.create(String.format("%s/%d", MAPPING_URI, id)))
                .body(ApiResponse.success());
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> put(
            @RequestBody Restaurant restaurant) {
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
}
