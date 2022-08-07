package com.foorun.unieat.constant;

public interface SwaggerApiInfo {
    String AUTH = "인증 Operations";
    String VERIFY_EMAIL = "이메일 인증 메일 전송";
    String VERIFY_EMAIL_SIGN_UP = "이메일 인증번호 검증";

    String MEMBER = "사용자 Operations";
    String SIGN_IN = "로그인 요청";
    String SIGN_UP = "회원가입 요청";
    String SIGN_UP_CHECK_EMAIL = "이메일 중복 확인";
    String SIGN_UP_CHECK_NICKNAME = "닉네임 중복 확인";
    String POST_VERIFY_EMAIL = "이메일 인증 메일 전송";
    String GET_VERIFY_EMAIL = "이메일 인증번호 검증";
    String RESET_PASSWORD = "비밀번호 초기화";
    String SETTING_NICKNAME = "닉네임 변경";
    String SETTING_PROFILE = "프로필 이미지 변경";
    String SETTING_NOTIFICATION = "알림 설정 여부 변경";
    String SETTING_PASSWORD = "비밀번호 변경";
    String SIGN_OUT = "로그아웃";
    String WITHDRAW = "회원 탈퇴";
    String RE_ISSUE_TOKEN = "인증 토큰 재발급";



    String POST = "게시글 Operations";
    String GET_POST_SUMMARY = "전체 게시판 카테고리별 게시글 목록 조회";
    String GET_POST_LIST = "게시글 목록 조회";
    String GET_POST = "게시글 단건 조회";
    String POST_POST = "게시글 생성";
    String DELETE_POST = "게시글 삭제";
    String PUT_POST = "게시글 수정";
    String POST_POST_FEELING = "게시글 좋아요";
    String DELETE_POST_FEELING = "게시글 좋아요 취소";
    String PUT_POST_FEELING = "게시글 좋아요/좋아요 취소";

    String COMMENT = "댓글 Operations";
    String GET_COMMENT_LIST = "댓글 목록 조회";
    String GET_COMMENT = "댓글 단건 조회";
    String POST_COMMENT = "댓글 생성";
    String PUT_COMMENT = "댓글 수정";
    String DELETE_COMMENT = "댓글 삭제";
    String POST_COMMENT_FEELING = "댓글 좋아요";
    String DELETE_COMMENT_FEELING = "댓글 좋아요 취소";
    String PUT_COMMENT_FEELING = "댓글 좋아요/좋아요 취소";
    String UPLOAD = "파일 업로드 Operations";
    String POST_UPLOAD = "파일 업로드";
    String STORE = "식당 정보 관련 operation";

    String GET_STORE_SIMPLE = "간략한 식당 정보 조회";
    String GET_STORE_DETAIL = "식당 상세 정보 조회";

    String SCHOOL = "학교 Operations";
    String GET_SCHOOL = "학교 목록 조회";

    String GET_STORE_BY_SEARCH = "검색을 이용한 식당 정보 조회";
    String GET_NEAREST_STORE = "주변 맛집 조회";
    String GET_SEARCH_LOG = "검색 기록 저장";
    String DELETE_SEARCH_LOG = "검색 기록 삭제";

    String POST_STORE_BY_FILTER = "필터를 이용한 식당 검색";
    String GET_TOP_STORE_TO_MAP = "top 맛집 지도에서 조회";


    String Review = "식당 리뷰 게시판 관련 operation";
    String POST_REVIEW_ADD = "식당 리뷰 작성";
    String DELETE_REVIEW = "식당 리뷰 삭제";
    String GET_REVIEW_LIST = "식당 리뷰 리스트 조회";
    String GET_REVIEW = "리뷰 상세 조회";
    String PATCH_REVIEW = "리뷰 수정";
    String POST_REVIEW_LIKING = "리뷰 좋아요 하기";
    String DELETE_REVIEW_LIKING = "리뷰 좋아요 취소";
    String GET_REVIEW_LIST_BY_RESTAURANT = "특정 식당의 리뷰 리스트 조회";




    String POST_BOOKMARKING = "식당 id를 통한 좋아요 누르기";
    String GET_BOOMARKEDLIST = "좋아요 누른 식당 리스트 가져오기";
    String DELETE_BOOKMARKING = "식당 id를 통한 좋아요 취소";



    String EVENT = "이벤트 게시판 관련 operation";
    String GET_EVENT_LIST = "현재 등록된 이벤트 목록 가져오기";
    String DELETE_USE_COUPON = "쿠폰 사용하기";
    String GET_EVENT_VALID_CHECK = "이벤트 유효성 체크 요청";

    String ADDRESS = "주소 관련 Operations";
    String GET_COORDINATE = "주소로 좌표 검색";


    String REPORT = "신고 관련 operation";
    String REPORTING = "특정 유저 신고하기";
    String UNREPORTING = "임의로 정지 유저 해제";

}
