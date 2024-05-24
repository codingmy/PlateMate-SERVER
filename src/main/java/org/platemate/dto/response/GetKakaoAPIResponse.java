package org.platemate.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetKakaoAPIResponse {
    public List<Document> documents;

    @Data
    static class Document {
        public String place_name;
        public String category_name;
        public String phone;
        public String road_address_name;
        public String place_url;
    }
}
