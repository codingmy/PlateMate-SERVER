package org.platemate.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.platemate.dto.request.GetTeamMappingRequest;
import org.platemate.dto.request.PostUserDataRequest;
import org.platemate.dto.response.PostUserDataResponse;
import org.platemate.exception.ErrorMessage;
import org.platemate.exception.SuccessMessage;
import org.platemate.global.response.ApiResponse;
import org.platemate.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserController {
    private UserService userService;

    //인증번호 요청 api
    @PostMapping
    public ApiResponse<PostUserDataResponse> postUserData(@RequestBody PostUserDataRequest request) {
        PostUserDataResponse teamCode = userService.postUserData(request);
        if (teamCode != null)
            return ApiResponse.success(SuccessMessage.USER_CREATE_SUCCESS, teamCode);
        else return ApiResponse.error(ErrorMessage.USER_NOT_SAVED_EXCEPTION);
    }

    //인증번호로 팀매핑 api
    @GetMapping
    public ApiResponse getTeamMapping(@RequestBody GetTeamMappingRequest request) {
        if (userService.getTeamMapping(request))
            return ApiResponse.success(SuccessMessage.CODE_SEARCH_SUCCESS);
        else return ApiResponse.error(ErrorMessage.CODE_NOT_FOUND_EXCEPTION);
    }
}
