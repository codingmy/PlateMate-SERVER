package org.platemate.dto.request;

public record PostUserDataRequest(
        String nickname,
        Long latitude,
        Long longtitude
) {
}
