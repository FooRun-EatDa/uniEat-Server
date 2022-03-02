package com.foorun.unieat.constant;

public interface SwaggerApiInfo {
    String MEMBER = "사용자 Operations";
    String SIGN_IN = "로그인 요청";
    String SIGN_UP = "회원가입 요청";
    String SIGN_UP_CHECK_EMAIL = "이메일 중복 확인";
    String SIGN_UP_CHECK_NICKNAME = "닉네임 중복 확인";
    String POST_VERIFY_EMAIL = "이메일 인증 메일 전송";
    String GET_VERIFY_EMAIL = "이메일 인증번호 검증";
    String RESET_PASSWORD = "비밀번호 초기화";

    String POST = "게시글 Operations";
    String GET_POST_SUMMARY = "전체 게시판 카테고리별 게시글 목록 조회";
    String GET_POST_LIST = "게시글 목록 조회";
    String GET_POST = "게시글 단건 조회";
    String POST_POST = "게시글 생성";
    String DELETE_POST = "게시글 삭제";
    String PUT_POST = "게시글 수정";

    String COMMENT = "댓글 Operations";
    String POST_COMMENT = "댓글 생성";

    String GET_STORE_SIMPLE = "간략한 식당 정보 조회";
    String GET_STORE_DETAIL = "식당 상세 정보 조회";

    String SCHOOL = "학교 Operations";
    String GET_SCHOOL = "학교 목록 조회";
}
