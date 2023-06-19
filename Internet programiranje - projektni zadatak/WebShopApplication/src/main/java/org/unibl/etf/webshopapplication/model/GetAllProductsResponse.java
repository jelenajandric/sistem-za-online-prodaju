package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private int id;
    private String title;
    private String description;
    private double price;
    private boolean isNew;
    private String location;
    private String categoryName;
    private String clientUsernameSelling;
    private String image;
}