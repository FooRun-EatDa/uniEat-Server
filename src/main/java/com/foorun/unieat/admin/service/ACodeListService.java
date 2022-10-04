package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.domain.code.dto.ACategory;
import com.foorun.unieat.domain.code.category.repository.CategoryCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ACodeListService {
    private final CategoryCodeRepository categoryCodeRepository;

    @Transactional(readOnly = true)
    public List<ACategory> fetch() {
        return categoryCodeRepository.findAll()
                .stream()
                .map(ACategory::of)
                .collect(Collectors.toList());
    }
}
