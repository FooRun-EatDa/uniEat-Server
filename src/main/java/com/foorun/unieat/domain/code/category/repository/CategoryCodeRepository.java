package com.foorun.unieat.domain.code.category.repository;

import com.foorun.unieat.domain.code.category.jpo.CategoryCodeJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCodeRepository extends JpaRepository<CategoryCodeJpo, Long> {
}
