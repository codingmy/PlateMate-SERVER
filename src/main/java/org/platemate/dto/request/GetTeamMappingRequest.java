package org.platemate.dto.request;

public record GetTeamMappingRequest(
        String nickname,
        Float latitude,
        Float longitude,
        Long teamAuthCode
) {
}
