package org.platemate.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.platemate.domain.TeamMapping;
import org.platemate.domain.User;
import org.platemate.dto.request.GetTeamMappingRequest;
import org.platemate.dto.request.PostUserDataRequest;
import org.platemate.dto.response.PostUserDataResponse;
import org.platemate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //유저가 팀 인증번호 발급요청시 로직
    //팀 인증번호 담은 response 리턴
    @Transactional
    public PostUserDataResponse postUserData(PostUserDataRequest request) {
        userRepository.postUserData(request.nickname(), request.latitude(), request.longitude());
        Long userId = userRepository.getUserIdByUserData(request.nickname(), request.latitude(), request.longitude());
        Long teamAuthCode = userRepository.creatTeamData(userId);
        return new PostUserDataResponse(teamAuthCode);
    }

    //인증번호로 팀매핑 진행
    @Transactional
    public Boolean getTeamMapping(GetTeamMappingRequest request) {
        //db에 유저 정보 업로드
        userRepository.postUserData(request.nickname(), request.latitude(), request.longitude());
        Long userId = userRepository.getUserIdByUserData(request.nickname(), request.latitude(), request.longitude());
        TeamMapping teamMappingData = userRepository.getTeamDataByTeamCode(request.teamAuthCode());

        //인증코드가 유효할 때(조회 가능, 매핑전)
        if (teamMappingData != null && teamMappingData.getIsMapped() == false) {
            //db에 유저 매핑 업데이트
            userRepository.updateTeamMapping(request.teamAuthCode(), userId);
            return true;
        } else
            //팀매핑 정보 없을 때(인증번호가 잘못된 경우)
            return false;
    }

    @Transactional
    public List<Float> getTeamUserMidPlaceData(Long authCode) {
        //인증번호로 team에서 userId2개 가져오기
        TeamMapping teamData = userRepository.getTeamDataByTeamCode(authCode);

        //userId 로 각각 유저의 위도 경도 get
        User user1Data = userRepository.getUserDataByUserId(teamData.getUser1_id());
        User user2Data = userRepository.getUserDataByUserId(teamData.getUser2_id());

        Float midLongtitude = (user1Data.getLongtitude() + user2Data.getLongtitude()) / 2;
        Float midLatitude = (user1Data.getLatitude() + user2Data.getLatitude()) / 2;

        List<Float> locationData = new ArrayList<Float>();
        locationData.add(midLongtitude);
        locationData.add(midLatitude);
        return locationData;
    }
}