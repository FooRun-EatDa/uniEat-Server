package com.foorun.unieat.service.event;

import com.foorun.unieat.constant.ServiceConstant;
import com.foorun.unieat.domain.coupon.repository.CouponQuerydslRepository;
import com.foorun.unieat.domain.event.EventQuerydslRepository;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.Role;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.service.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.util.Optional;

import static com.foorun.unieat.constant.ServiceConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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


        Assertions.assertEquals(eventService.isCouponValid(memberUserDetails,eventId), COUPON_VALID);

    }


}
