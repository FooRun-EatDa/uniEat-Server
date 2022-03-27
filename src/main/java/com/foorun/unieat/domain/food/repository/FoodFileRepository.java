package com.foorun.unieat.domain.food.repository;

import com.foorun.unieat.domain.food.jpo.FoodFileIdJpo;
import com.foorun.unieat.domain.food.jpo.FoodFileJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodFileRepository extends JpaRepository<FoodFileJpo, FoodFileIdJpo> {
}
