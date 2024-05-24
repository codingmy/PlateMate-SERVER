package org.platemate.dto.request;

public record PostUserDataRequest(
        String nickname,
        Float latitude,
        Float longtitude
) {
}
