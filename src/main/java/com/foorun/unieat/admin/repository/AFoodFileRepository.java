package com.foorun.unieat.admin.repository;

import com.foorun.unieat.domain.food.jpo.FoodFileIdJpo;
import com.foorun.unieat.domain.food.jpo.FoodFileJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFoodFileRepository extends JpaRepository<FoodFileJpo, FoodFileIdJpo> {
}
