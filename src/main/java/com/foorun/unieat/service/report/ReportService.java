package com.foorun.unieat.service.report;

import com.fasterxml.jackson.databind.introspect.MemberKey;
import com.foorun.unieat.domain.common.StatusType;
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
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import org.hibernate.mapping.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;



    @Transactional
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
        blockChecking(toMember);


    }

    private void blockChecking(MemberJpo memberJpo) {
        Long count = getReportCount(memberJpo);
        if(count >= 3){
            if(count >= 10){
                memberJpo.setStatus(StatusType.REMOVED); //영구 정지 추후 삭제

            }
            else{
                memberJpo.setStatus(StatusType.INACTIVE); //일시적으로 inactive
                memberJpo.setUpdatedAt(LocalDateTime.now()); //정지된 날짜 업데이트
            }
        }
    }

    private Long getReportCount(MemberJpo toMember){
       return reportRepository.getReportedCount(toMember.getId());
    }


    /**
     * 임의로 사용자 정지 해제
     */
    @Transactional
    public void exitBlocked(Long memberId){
        MemberJpo memberJpo = memberRepository.findById(memberId).orElseThrow(UniEatNotFoundException::new);
        memberJpo.setStatus(StatusType.ACTIVE);
        //신고 당한 내역 모두 삭제
        for (ReportJpo r : reportRepository.findAllByToMember(memberJpo)) {
            reportRepository.delete(r);
        }

    }


}
