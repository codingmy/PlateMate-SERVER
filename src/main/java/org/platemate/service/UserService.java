package org.platemate.service;

import org.platemate.dto.request.PostUserDataRequest;
import org.platemate.dto.response.PostUserDataResponse;
import org.platemate.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public PostUserDataResponse postUserData(PostUserDataRequest request) {
        return new PostUserDataResponse(userRepository.postUserData(request.nickname(), request.latitude(), request.longtitude()));
    }
}
