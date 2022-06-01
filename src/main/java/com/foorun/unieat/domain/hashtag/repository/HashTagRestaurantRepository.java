package com.foorun.unieat.domain.hashtag.repository;

import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRestaurantRepository extends JpaRepository<HashTagRestaurantJpo,Long> {
}
