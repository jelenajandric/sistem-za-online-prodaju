package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyingProductRequest {
    private int productId;
    private String clientUsername;
    private String paymentMethod;
    private String cardNumber;
}
