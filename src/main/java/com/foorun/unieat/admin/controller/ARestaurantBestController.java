package com.foorun.unieat.admin.controller;

import com.foorun.unieat.admin.service.ARestaurantBestListService;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ARestaurantBestController.MAPPING_URI, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(hidden = true)
public class ARestaurantBestController {
    public final static String MAPPING_URI = "/admin/restaurant/best";
    private final ARestaurantBestListService restaurantBestListService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Restaurant>>> get() {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantBestListService.fetch()));
    }
}
