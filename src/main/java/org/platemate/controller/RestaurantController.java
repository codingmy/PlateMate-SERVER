package org.platemate.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.platemate.dto.request.GetRestaurantListRequestV1;
import org.platemate.dto.request.GetRestaurantListRequestV2;
import org.platemate.dto.request.PostUserDataRequest;
import org.platemate.dto.response.GetKakaoAPIResponse;
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
    @GetMapping("/V1")
    public GetKakaoAPIResponse getRestaurantListV1(@RequestBody GetRestaurantListRequestV1 request) {
        //바로 카카오 open api 사용해서 서버통신 해서 정보 받아오기
        return this.getRestListFromKakaoAPI(request.longitude(), request.latitude());
    }

    @GetMapping("/V2")
    public GetKakaoAPIResponse getRestaurantListV2(@RequestBody GetRestaurantListRequestV2 request) {
        List<Float> locationData = userService.getTeamUserMidPlaceData(request.teamAuthCode());
        //바로 카카오open api 사용해서 서버통신해서 정보 받아오기
        return this.getRestListFromKakaoAPI(locationData.get(0), locationData.get(1));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getRestListFromKakaoAPIToString(@RequestBody PostUserDataRequest request/* Long longti, Long lati*/){
        return kakaoAPIService.getRestaurantList(request.longtitude(), request.latitude());
    }*/
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public GetKakaoAPIResponse getRestListFromKakaoAPIToString(@RequestBody PostUserDataRequest request ){
        return kakaoAPIService.getRestaurantList(request.longtitude(), request.latitude());
    }
}
