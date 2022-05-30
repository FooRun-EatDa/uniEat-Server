package com.foorun.unieat.admin.repository;

import com.foorun.unieat.domain.food.jpo.FoodJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFoodRepository extends JpaRepository<FoodJpo, Long> {
}
