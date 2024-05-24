package org.platemate.service;

import org.platemate.dto.request.PostUserDataRequest;
import org.platemate.dto.response.PostUserDataResponse;
import org.platemate.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    //유저가 팀 인증번호 발급요청시 로직
    //팀 인증번호 담은 response 리턴
    public PostUserDataResponse postUserData(PostUserDataRequest request) {
        Long userId = userRepository.postUserData(request.nickname(), request.latitude(), request.longtitude());
        Long teamAuthCode = userRepository.creatTeamData(userId);
        return new PostUserDataResponse(teamAuthCode);
    }
}
