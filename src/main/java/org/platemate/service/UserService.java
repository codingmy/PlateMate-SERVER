package org.platemate.service;

import org.platemate.domain.TeamMapping;
import org.platemate.dto.request.GetTeamMappingRequest;
import org.platemate.dto.request.PostUserDataRequest;
import org.platemate.dto.response.PostUserDataResponse;
import org.platemate.exception.ErrorMessage;
import org.platemate.repository.UserRepository;
import org.webjars.NotFoundException;

public class UserService {
    private UserRepository userRepository;

    //유저가 팀 인증번호 발급요청시 로직
    //팀 인증번호 담은 response 리턴
    public PostUserDataResponse postUserData(PostUserDataRequest request) {
        Long userId = userRepository.postUserData(request.nickname(), request.latitude(), request.longtitude());
        Long teamAuthCode = userRepository.creatTeamData(userId);
        return new PostUserDataResponse(teamAuthCode);
    }

    //인증번호로 팀매핑 진행
    public Boolean getTeamMapping(GetTeamMappingRequest request) {
        //db에 유저 정보 업로드
        Long userId = userRepository.postUserData(request.nickname(), request.latitude(), request.longtitude());
        TeamMapping teamMappingData = userRepository.getTeamDataByTeamCode(request.authCode());

        //인증코드가 유효할 때(조회 가능, 매핑전)
        if (teamMappingData != null && teamMappingData.getIsMapped() == false) {
            //db에 유저 매핑 업데이트
            userRepository.updateTeamMapping(userId);
            return true;
        } else
            //팀매핑 정보 없을 때(인증번호가 잘못된 경우)
            return false;
    }
}
