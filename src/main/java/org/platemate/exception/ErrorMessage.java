package org.platemate.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {
    CODE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "인증번호 정보가 존재하지 않습니다."),
    USER_NOT_SAVED_EXCEPTION(HttpStatus.NOT_FOUND, "유저 정보가 저장되지 않았습니다.");
    private final HttpStatus status;
    private final String message;
}
