package com.foorun.unieat.admin.controller;

import com.foorun.unieat.admin.domain.event.dto.AEvent;
import com.foorun.unieat.admin.service.AEventListService;
import com.foorun.unieat.admin.service.AEventService;
import com.foorun.unieat.domain.common.api.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = AEventController.MAPPING_URI, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(hidden = true)
public class AEventController {
    public final static String MAPPING_URI = "/admin/event";
    private final AEventService eventService;
    private final AEventListService eventListService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AEvent>>> get() {
        return ResponseEntity.ok(
                ApiResponse.valueOf(eventListService.fetch()));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<AEvent>> get(
            @PathVariable("eventId") long eventId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(eventService.fetch(eventId)));
    }

    /**
     * @param restaurantIdsJoinedStr restaurantId=1902151,123,500
     */
    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable("eventId") long eventId) {
        eventService.remove(eventId);
        return ResponseEntity.ok(
                ApiResponse.success());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AEvent>> post(
            @RequestBody AEvent event) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(eventService.save(event)));
    }
}
