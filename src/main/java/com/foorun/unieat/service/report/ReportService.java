package com.foorun.unieat.service.report;

import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.report.ReportType;
import com.foorun.unieat.domain.report.dto.Report;
import com.foorun.unieat.domain.report.jpo.ReportJpo;
import com.foorun.unieat.domain.report.repository.ReportRepository;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;


    public void reporting(Long reportType, Report report, MemberUserDetails  memberUserDetails){

        ReportType type = Arrays.stream(ReportType.values())
                .filter(r->r.getNumber().equals(reportType))
                .findFirst()
                .orElseThrow(UniEatLogicalException::new);

        MemberJpo fromMember = memberRepository.findById(memberUserDetails.getId()).orElseThrow(UniEatNotFoundException::new);
        MemberJpo toMember = memberRepository.findById(report.getTo_member()).orElseThrow(UniEatNotFoundException::new);

        ReportJpo reportJpo = ReportJpo.builder()
                .id(IdentifyGenerator.number())
                .reportType(type)
                .fromMember(fromMember)
                .toMember(toMember)
                .content(report)
                .build();
        reportRepository.save(reportJpo);

        //신고 횟수 3번 30일 차단


        //신고 횟수 10번 영구 차단

    }








}
