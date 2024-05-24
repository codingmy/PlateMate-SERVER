package org.platemate.dto.request;

public record GetTeamMappingRequest (
        String nickname,
        Long latitude,
        Long longtitude,
        Long authCode
){
}
