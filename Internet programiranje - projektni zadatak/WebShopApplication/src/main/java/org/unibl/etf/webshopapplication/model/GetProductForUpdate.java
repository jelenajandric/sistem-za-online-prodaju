package org.unibl.etf.webshopapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductForUpdate {
    private int id;
    private String title;
    private String description;
    private double price;
    @JsonProperty("isNew")
    private String isNew;
    private String location;
    private String contact;
    private String category;
    private String clientUsernameSeller;
}
