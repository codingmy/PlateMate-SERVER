package org.platemate.dto.response;

public record GetTeamMappingResponse(
        Boolean result
){
    public GetTeamMappingResponse(Boolean result) {
        this.result = result;
    }
}
