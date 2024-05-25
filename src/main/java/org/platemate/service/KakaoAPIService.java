package org.platemate.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.platemate.dto.response.GetKakaoAPIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@Transactional
public class KakaoAPIService {

    @Value("${kakao_id}")
    private String kakaoAPIKey;
    private String FORMAT = "JSON?";
    private String coordForm = "WGS84";
    private String categoryCode = "FD6";

    public GetKakaoAPIResponse getRestaurantList(Float longti, Float lati) {

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoAPIKey);

        RestTemplate restTemplate = new RestTemplate();

        String apiURL = "https://dapi.kakao.com/v2/local/search/keyword.JSON?" +
                "query=" + "맛집"//query
                + "&category_group_code=" + "FD6"
                + "&x=" + longti
                + "&y=" + lati
                + "&radius=" + "100";
        System.out.println(apiURL);
        System.out.println(lati);
        final HttpEntity<String> entity = new HttpEntity<>(headers);

//        System.out.println(restTemplate.exchange(apiURL, HttpMethod.GET, entity,String.class).toString());

        return restTemplate.exchange(apiURL, HttpMethod.GET, entity, GetKakaoAPIResponse.class).getBody();

    }
/*
    public String GetKakaoAPIResponse getRestaurantList(Long longtitude, Long latitude) {
        try {
            String query = URLEncoder.encode("맛집", "UTF-8");
            final HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + kakaoAPIKey);

            RestTemplate restTemplate = new RestTemplate();
            //request url 생성
            String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.JSON?"
                    + "query=" + query
                    + "@category_group_code=" + categoryCode
                    + "&x=" + longtitude
                    + "&y=" + latitude
                    + "&output_coord=" + coordForm;
            final HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(apiUrl, HttpMethod.GET, entity, )


            return restTemplate.exchange(apiUrl, HttpMethod.GET, entity, GetKakaoAPIResponse.class).getBody();
*/

/*

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
*/
/*
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }*/

}
