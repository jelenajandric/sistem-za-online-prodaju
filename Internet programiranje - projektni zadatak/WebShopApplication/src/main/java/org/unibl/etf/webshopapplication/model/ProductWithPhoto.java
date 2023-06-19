package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithPhoto {
    private int id;
    private String title;
    private String description;
    private double price;
    private boolean isNew;
    private String location;
    private String contact;
    private int categoryId;
    private int clientIdSelling;
    private String path;
}
