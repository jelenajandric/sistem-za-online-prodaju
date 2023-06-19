package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyingProduct {
    private int productId;
    private int clientId;
    private String paymentMethod;
    private Date buyingDate;
}
