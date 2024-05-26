package org.platemate;

import org.platemate.dto.response.GetTeamMappingResponse;
import org.platemate.exception.SuccessMessage;
import org.platemate.global.response.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println(ApiResponse.success(SuccessMessage.CODE_SEARCH_SUCCESS, new GetTeamMappingResponse(true)));
        SpringApplication.run(Main.class, args);
    }
}