package com.foorun.unieat.domain.school.repository;

import com.foorun.unieat.domain.school.jpo.SchoolJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolJpo, Long> {
}
