package org.platemate.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.platemate.dto.request.GetRestaurantListRequestV1;
import org.platemate.dto.request.GetRestaurantListRequestV2;
import org.platemate.dto.response.GetRestaurantListResponse;
import org.platemate.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantController {
    private UserService userService;

    @GetMapping("/V1")
    public GetRestaurantListResponse getRestaurantListV1(@RequestBody GetRestaurantListRequestV1 request) {

    }

    @GetMapping("/V2")
    public GetRestaurantListResponse getRestaurantListV2(@RequestBody GetRestaurantListRequestV2 request) {
        List<Long> locationData = userService.getTeamUserMidPlaceData(request.teamAuthCode());
    }
}
