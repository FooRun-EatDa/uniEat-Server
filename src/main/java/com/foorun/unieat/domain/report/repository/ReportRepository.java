package com.foorun.unieat.domain.report.repository;

import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.report.jpo.ReportJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportJpo,Long> {

    @Query("SELECT COUNT(r) FROM ReportJpo r WHERE r.toMember.id = :memberId")
    Long getReportedCount(@Param(value = "memberId")Long memberId);

    List<ReportJpo> findAllByToMember(MemberJpo toMember);

}
