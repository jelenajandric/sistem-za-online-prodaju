package org.unibl.etf.webshopapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private int id;
    private String title;
    private String description;
    private double price;
    @JsonProperty("isNew")
    private boolean isNew;
    private String location;
    private String contact;
    @JsonProperty("isSold")
    private boolean isSold;
    private String categoryName;
    private String clientUsernameSelling;
    private List<GetAllImagesResponse> images;
    private Map<String, String> attributesAndValues;
}
