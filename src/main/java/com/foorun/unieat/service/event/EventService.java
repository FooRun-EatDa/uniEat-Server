package com.foorun.unieat.service.event;

import com.foorun.unieat.domain.coupon.repository.CouponQuerydslRepository;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.foorun.unieat.constant.ResponseCode.CODE_1000;
import static com.foorun.unieat.constant.ServiceConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final RestaurantRepository restaurantRepository;
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
     * 해당 이벤트 쿠폰 사용 버튼 가능 여부 검사
     */
    public EventValidResponse isCouponExpired(MemberUserDetails memberUserDetails, Long eventId) throws ParseException {

        EventJpo event = eventQuerydslRepository.find(eventId).orElseThrow(UniEatBadRequestException::new);

        if(!EventExpiredCheck(event)){
            return EventValidResponse.builder()
                    .content(EVENT_EXPIRED)
                    .status(EventStatus.EXPIRED.ordinal())
                    .build();
        }


        else return EventValidResponse.builder()
                .content(COUPON_VALID)
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
     * 쿠폰 사용하기, 버튼 누르기
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void useCoupon(Long eventId){
        EventJpo event = eventRespository.findById(eventId).orElseThrow(UniEatNotFoundException::new);
        //이벤트 선착순 유효성 검사(쿠폰 사용인원 도달 했는지)

        if(isEventCouponRemain(event)){
            event.subtractCouponCountByOne();
        }
        else {
            log.trace("잔여 이벤트 쿠폰 없음");
            throw new UniEatBadRequestException(CODE_1000);
        }


    }

    private boolean isEventCouponRemain(EventJpo event){
        if(event.getCouponCount() > 0) return true;
        else return false;
    }



}
