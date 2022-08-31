package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.event.dto.AEvent;
import com.foorun.unieat.domain.event.EventRespository;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AEventService {
    private final EventRespository eventRespository;

    @Transactional(readOnly = true)
    public AEvent fetch(long eventId) {
        return AEvent.of(eventRespository.findById(eventId).orElseThrow(UniEatNotFoundException::new));
    }

    @Transactional
    public AEvent save(AEvent event) {
        EventJpo eventJpo = eventRespository.findById(event.getId())
                .orElseGet(event::asJpo);
        event.applyRevision(eventJpo);
        return AEvent.of(eventRespository.save(eventJpo));
    }

    @Transactional
    public void remove(long eventId) {
        //  TODO : implementation
    }
}
