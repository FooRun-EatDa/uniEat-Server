package com.foorun.unieat.service.event;

import com.foorun.unieat.domain.event.EventQuerydslRepository;
import com.foorun.unieat.domain.event.EventRespository;
import com.foorun.unieat.domain.event.dto.Event;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    
    private final RestaurantRepository restaurantRepository;
    private final EventRespository eventRespository;
    private final EventQuerydslRepository eventQuerydslRepository;


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



}
