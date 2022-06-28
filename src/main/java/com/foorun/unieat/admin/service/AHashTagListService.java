package com.foorun.unieat.admin.service;

import com.foorun.unieat.domain.hashtag.dto.HashTag;
import com.foorun.unieat.domain.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AHashTagListService {
    private final HashTagRepository hashTagRepository;

    @Transactional(readOnly = true)
    public List<HashTag> fetch() {
        return hashTagRepository.findAll()
                .stream()
                .map(HashTag::of)
                .collect(Collectors.toList());
    }
}
