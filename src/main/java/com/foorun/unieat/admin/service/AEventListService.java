package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.event.dto.AEvent;
import com.foorun.unieat.domain.event.EventRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AEventListService {
    private final EventRespository eventRespository;

    @Transactional(readOnly = true)
    public List<AEvent> fetch() {
        return eventRespository.findAll()
                .stream()
                .map(AEvent::of)
                .collect(Collectors.toList());
    }
}
