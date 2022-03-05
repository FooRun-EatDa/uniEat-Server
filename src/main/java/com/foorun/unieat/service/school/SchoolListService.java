package com.foorun.unieat.service.school;

import com.foorun.unieat.domain.school.dto.School;
import com.foorun.unieat.domain.school.repository.SchoolQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolListService {
    private final SchoolQuerydslRepository schoolQuerydslRepository;

    public List<School> fetchList(String keyword) {
        return schoolQuerydslRepository.findByKeywordRegex(keyword)
                .stream()
                .map(School::of)
                .collect(Collectors.toList());
    }
}
