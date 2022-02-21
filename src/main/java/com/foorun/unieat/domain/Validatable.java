package com.foorun.unieat.domain;

/**
 * 프로퍼티 검증을 필요로 하는 클래스에 구현한다.
 * 주로 클라이언트로부터 API 요청 양식을 매핑하는 Request DTO 클래스에 사용할 수 있음.
 */
public interface Validatable {
    void validate();
}
