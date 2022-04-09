package com.foorun.unieat.domain.review.repository;

import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewJpo,Long> {

}
