package org.platemate.dto.request;

public record GetAuthCodeRequest(
        String nickname,
        Long latitude,
        Long longtitude
) {
}
