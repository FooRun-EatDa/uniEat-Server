package com.foorun.unieat.service.event;

import com.foorun.unieat.domain.coupon.entity.CouponJpo;
import com.foorun.unieat.domain.coupon.repository.CouponQuerydslRepository;
import com.foorun.unieat.domain.coupon.repository.CouponRepository;
import com.foorun.unieat.domain.event.EventQuerydslRepository;
import com.foorun.unieat.domain.event.EventRespository;
import com.foorun.unieat.domain.event.EventStatus;
import com.foorun.unieat.domain.event.dto.Event;
import com.foorun.unieat.domain.event.dto.EventValidResponse;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.foorun.unieat.constant.ServiceConstant.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final RestaurantRepository restaurantRepository;
    private final CouponRepository couponRepository;
    private final EventRespository eventRespository;
    private final EventQuerydslRepository eventQuerydslRepository;
    private final CouponQuerydslRepository couponQuerydslRepository;
    private final MemberRepository memberRepository;

    /**
     * 이벤트 리스트 조회
     */
    public List<Event> getEventList(PageRequest pageRequest){
        return eventQuerydslRepository.find(pageRequest)
                .stream().map(Event::of).collect(Collectors.toList());
    }

    /**
     * 이벤트 상세 조회
     */
    public Event getEventDetail(Long eventId){
        return Event.of(eventQuerydslRepository.find(eventId).orElseThrow(UniEatBadRequestException::new));
    }


    /**
     * 해당 이벤트 쿠폰 유효 여부 검사
     */
    public EventValidResponse isCouponValid(MemberUserDetails memberUserDetails, Long eventId) throws ParseException {

        EventJpo event = eventQuerydslRepository.find(eventId).orElseThrow(UniEatBadRequestException::new);

        if(!EventExpiredCheck(event)){
            return EventValidResponse.builder()
                    .desc(COUPON_EXPIRED)
                    .status(EventStatus.EXPIRED.ordinal())
                    .build();
        }

        MemberJpo member = memberRepository.findByEmail(memberUserDetails.getEmail())
                .orElseThrow(UniEatNotFoundException::new);

        if(!validCheck(event,member)){
            return EventValidResponse.builder()
                    .desc(COUPON_NOT_APPLICABLE)
                    .status(EventStatus.NOT_APPLICABLE.ordinal())
                    .build();
        }

        else return EventValidResponse.builder()
                .desc(COUPON_VALID)
                .status(EventStatus.VALID.ordinal())
                .build();


    }

    //쿠폰 사용했는지 확인
    private boolean validCheck(EventJpo event,MemberJpo member) {
        return couponQuerydslRepository.existByEventIdAndMemberId(event.getId(),member.getId());

    }

    public boolean EventExpiredCheck(EventJpo event) throws ParseException {
        SimpleDateFormat eventExpiredDateSimple = new SimpleDateFormat(DATE_FORMAT);
        Date eventExpiredDate = eventExpiredDateSimple.parse(event.getExpiredDate());
        Date now = DateUtil.getDate();
        if(now.before(eventExpiredDate)){
            return true;
        }
        else return false;
    }

    /**
     * 쿠폰 사용하기
     */
    public void useCoupon(MemberUserDetails memberUserDetails,Long eventId){
        CouponJpo couponJpo = couponQuerydslRepository
                .findCouponByEventIdAndMemberId(eventId,memberUserDetails.getId())
                .orElseThrow(UniEatNotFoundException::new);
        couponRepository.delete(couponJpo);

    }

}
