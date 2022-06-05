package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.domain.common.paging.Paging;
import com.foorun.unieat.domain.event.dto.Event;
import com.foorun.unieat.domain.event.dto.EventValidResponse;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.service.event.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static com.foorun.unieat.constant.ServiceConstant.PAGING_SIZE;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.EVENT)
public class EventController {


    private final EventService eventService;


    @ApiOperation(value = SwaggerApiInfo.GET_EVENT_LIST, notes = "현재 등록된 이벤트 목록 조회, 페이지 별로 일정 갯수(10) 만큼 전달 ")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Event>>> getEventList(@RequestParam(name="page") int page){
        return ResponseEntity.ok(
                ApiResponse.valueOf(eventService.getEventList(new Paging(page, PAGING_SIZE)))
        );
    }


    //쿠폰사용
    @ApiOperation(value = SwaggerApiInfo.DELETE_USE_COUPON, notes = "해당 이벤트 쿠폰 사용하기, 쿠폰 테이블에서 해당 엔티티 삭제")
    @DeleteMapping("/{event_id}")
    public ResponseEntity<ApiResponse<Void>> useEventCoupon(@AuthenticationPrincipal MemberUserDetails memberUserDetails,
                                                            @PathVariable(name = "event_id") Long eventId){
        eventService.useCoupon(memberUserDetails,eventId);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @ApiOperation(value = SwaggerApiInfo.GET_EVENT_VALID_CHECK, notes = "해당 이벤트에 대한 쿠폰의 상태를 확인함," +
            " status code :   " +
            " VALID(진행중) = 0 \n" +
            " EXPIRED(만료됨) = 1 \n" +
            " NOT_APPLICABLE(이미 사용함) = 2")
    @GetMapping("/{event_id}/validCheck")
    public ResponseEntity<ApiResponse<EventValidResponse>> couponValidCheck(@AuthenticationPrincipal MemberUserDetails memberUserDetails,
                                                                            @PathVariable(name = "event_id") Long eventId) throws ParseException {

        return ResponseEntity.ok(ApiResponse.valueOf(eventService.isCouponValid(memberUserDetails,eventId)));

    }
}
