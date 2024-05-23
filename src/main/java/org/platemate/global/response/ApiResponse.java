package org.platemate.global.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.platemate.exception.ErrorMessage;
import org.platemate.exception.SuccessMessage;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    private final int status;
    private final String message;
    private T data;

    //통신 성공시 response body의 데이터 있는 경우(get) Response
    public static <T> ApiResponse<T> success(final SuccessMessage success, T data) {
        return new ApiResponse<>(success.getStatus().value(), success.getMessage(), data);
    }

    //통신 성공시 response body의 데이터 없는 경우 Response
    public static ApiResponse success(final SuccessMessage success) {
        return new ApiResponse(success.getStatus().value(), success.getMessage());
    }

    //통신 실패시
    public static <T> ApiResponse<T> error(final ErrorMessage error, T data) {
        return new ApiResponse<>(error.getStatus().value(), error.getMessage(), data);
    }

    //통신 실패시
    public static ApiResponse error(final ErrorMessage error) {
        return new ApiResponse(error.getStatus().value(), error.getMessage());
    }
}