package com.foorun.unieat.service.review;

import com.foorun.unieat.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFeelingService {

    private final ReviewRepository reviewRepository;

}
