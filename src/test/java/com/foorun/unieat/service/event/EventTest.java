package com.foorun.unieat.service.event;

import com.foorun.unieat.domain.coupon.repository.CouponQuerydslRepository;
import com.foorun.unieat.domain.event.EventQuerydslRepository;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.service.ServiceTest;
import com.foorun.unieat.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.foorun.unieat.constant.ServiceConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventTest extends ServiceTest {

    @Mock
    private CouponQuerydslRepository couponQuerydslRepository;
    @Mock
    private EventQuerydslRepository eventQuerydslRepository;

    private final Long eventId = 1L;

    @InjectMocks
    private EventService eventService;

    private EventJpo event = EventJpo.builder()
            .id(eventId)
            .expiredDate("2099/12/31 12:30")
            .name("name")
            .desc("desc")
            .build();

    private MemberUserDetails memberUserDetails =
            MemberUserDetails.builder()
                    .id(memberId)
                    .email("hyun123@naver.com")
                    .role(Role.USER)
                    .nickname("user")
                    .build();

    private MemberJpo member = MemberJpo.builder()
            .id(memberUserDetails.getId())
            .email(memberUserDetails.getEmail())
            .name(memberUserDetails.getUsername()).build();
    @Test
    @DisplayName("쿠폰 유효성 검사 테스트 : 유효")
    void GIVEN_VALID_COUPON_THEN_RETURN_VALID() throws ParseException {

        when(eventQuerydslRepository.find(eventId)).thenReturn(Optional.ofNullable(event));
        when(couponQuerydslRepository.existByEventIdAndMemberId(anyLong(),anyLong())).thenReturn(true);
        when(memberRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(member));


        Assertions.assertEquals(COUPON_VALID,eventService.isCouponValid(memberUserDetails,eventId));

    }


    @Test
    @DisplayName("쿠폰 유효성 검사 테스트 : 갖고있는 쿠폰 없음")
    void GIVEN_NA_COUPON_THEN_RETURN_COUPON_NA() throws ParseException {
        when(eventQuerydslRepository.find(eventId)).thenReturn(Optional.ofNullable(event));
        when(couponQuerydslRepository.existByEventIdAndMemberId(anyLong(),anyLong())).thenReturn(false);
        when(memberRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(member));


        Assertions.assertEquals(COUPON_NOT_APPLICABLE,eventService.isCouponValid(memberUserDetails,eventId));

    }

    @Test
    @DisplayName("쿠폰 만료 테스트 : 쿠폰 기한 만료")
    void GIVEN_EXPIRED_COUPON_THEN_RETURN_EXPIRED()throws ParseException{

        //만료된 이벤트
        EventJpo event = EventJpo.builder()
                .id(eventId)
                .expiredDate("1999/12/31 12:30")
                .name("name")
                .desc("desc")
                .build();

        Assertions.assertEquals(false,eventService.EventExpiredCheck(event));

    }







}
