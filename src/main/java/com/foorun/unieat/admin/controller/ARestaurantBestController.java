package com.foorun.unieat.admin.controller;

import com.foorun.unieat.admin.domain.ARestaurant;
import com.foorun.unieat.admin.domain.restaurant.dto.ARestaurantBestDeletePayload;
import com.foorun.unieat.admin.service.ARestaurantBestListService;
import com.foorun.unieat.constant.JwtConstant;
import com.foorun.unieat.domain.common.api.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME_REFRESH_TOKEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ARestaurantBestController.MAPPING_URI, produces = APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*", exposedHeaders = {JwtConstant.HEADER_NAME, HEADER_NAME_REFRESH_TOKEN, HttpHeaders.LOCATION})
@RequiredArgsConstructor
@Api(hidden = true)
public class ARestaurantBestController {
    public final static String MAPPING_URI = "/admin/restaurant/best";
    private final ARestaurantBestListService restaurantBestListService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ARestaurant>>> get() {
        return ResponseEntity.ok(
                ApiResponse.valueOf(restaurantBestListService.fetch()));
    }

    /**
     * @param restaurantIdsJoinedStr restaurantId=1902151,123,500
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(
            @RequestParam("restaurantIds") String restaurantIdsJoinedStr) {
        restaurantBestListService.remove(restaurantIdsJoinedStr);
        return ResponseEntity.ok(
                ApiResponse.success());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> post(
            @RequestBody ARestaurantBestDeletePayload payload) {
        restaurantBestListService.saveAll(payload);
        return ResponseEntity.ok(
                ApiResponse.success());
    }
}
