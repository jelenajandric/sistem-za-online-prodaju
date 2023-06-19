package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private String title;
    private Integer categoryId;
    private String location;
    private Double priceFrom;
    private Double priceTo;
    private String newOrUsedOrAll;
    private Integer clientId;
    private Boolean isSold;
    HashMap<String, String> attributesAndValues;
}
