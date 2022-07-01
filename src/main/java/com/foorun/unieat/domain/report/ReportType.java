package com.foorun.unieat.domain.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 어떤 것에 대한 신고인지 구분하는 타입
 */
@Getter
@RequiredArgsConstructor
public enum ReportType {

    REVIEW(0L,"리뷰 신고"),
    POST(1L,"게시물 신고"),
    MESSAGE(2L,"쪽지 신고"),
    COMMENT(3L,"댓글 신고");



    private final Long number; //타입 일련번호
    private final String type;




}
