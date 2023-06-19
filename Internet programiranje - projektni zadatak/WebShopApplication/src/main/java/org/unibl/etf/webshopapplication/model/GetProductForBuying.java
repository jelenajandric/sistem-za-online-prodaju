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
public class GetProductForBuying {
    private int id;
    private String title;
    private String image;
    @JsonProperty("isSold")
    private boolean isSold;
}
