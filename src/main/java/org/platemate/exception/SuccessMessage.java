package org.platemate.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "사용자 정보 생성 완료"),
    CODE_SEARCH_SUCCESS(HttpStatus.OK, "인증번호 조회 성공"),
    OPEN_API_REQUEST_SUCCESS(HttpStatus.OK, "맛집 리스트 api 통신 성공");
    private final HttpStatus status;
    private final String message;
}
