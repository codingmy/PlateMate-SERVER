package org.platemate.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.platemate.dto.request.GetRestaurantListRequestV1;
import org.platemate.dto.request.GetRestaurantListRequestV2;
import org.platemate.dto.response.GetRestListFromKakaoResponse;
import org.platemate.dto.response.GetRestaurantListResponse;
import org.platemate.service.KakaoAPIService;
import org.platemate.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.platemate.dto.request.PostUserDataRequest;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantController {
    private UserService userService;
    private KakaoAPIService kakaoAPIService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getRestListFromKakaoAPIToString(@RequestBody PostUserDataRequest request/* Long longti, Long lati*/){
        return kakaoAPIService.getRestaurantList(request.longtitude(), request.latitude());
    }
}
