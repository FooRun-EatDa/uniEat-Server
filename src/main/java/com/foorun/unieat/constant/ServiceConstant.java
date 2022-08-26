package com.foorun.unieat.constant;

public class ServiceConstant {

    public static final int PAGING_SIZE = 10;
    public static final int INFINITY_NUM = 0x3f3f3f3f;
    public static final int NEAR_BY = 1; //1KM 까지 검색

    public static final String DATE_FORMAT = "yy/MM/dd HH:mm";

    public static final String EVENT_EXPIRED = "이벤트가 종료되었습니다.";

    public static final String COUPON_NOT_APPLICABLE = "사용가능한 쿠폰 없음";

    public static final String COUPON_VALID = "쿠폰 사용 가능";

    public static final String EVENT_NOTICE_DELIMITER="-";

    /**
     * 신고 사유 타입 번호 구분
     */
    public static final Long REPORT_REVIEW = 0L;
    public static final Long REPORT_POST = 1L;
    public static final Long REPORT_MESSAGE = 2L;
    public static final Long REPORT_COMMENT = 3L;


}
