package org.platemate.service;

import org.platemate.dto.response.GetRestListFromKakaoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.List;

@Service
public class KakaoAPIService {

    @Value("${kakao_id}")
    private String kakaoAPIKey;
    private String FORMAT = "JSON?";
    private String coordForm = "WGS84";
    private String categoryCode = "FD6";

    public /*List<GetRestListFromKakaoResponse>*/String getRestaurantList(Long longtitude, Long latitude) {
        try {
            String query = URLEncoder.encode("맛집", "UTF-8");

            //request url 생성
            String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.JSON?"
                    + "query=" + query
                    + "@category_group_code=" + categoryCode
                    + "&x=" + longtitude
                    + "&y=" + latitude
                    + "&output_coord=" + coordForm;
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //request 헤더 설정
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "KakaoAK " + kakaoAPIKey);

            //응답
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) {
                //정상 호출시
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                //에러일 때
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
