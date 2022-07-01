package com.foorun.unieat.domain.report.repository;

import com.foorun.unieat.domain.report.jpo.ReportJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportJpo,Long> {
}
