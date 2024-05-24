package org.platemate.dto.request;

public record GetTeamMappingRequest(
        String nickname,
        Float latitude,
        Float longtitude,
        Long authCode
) {
}
