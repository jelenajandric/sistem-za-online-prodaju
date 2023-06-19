package org.unibl.etf.webshopapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.unibl.etf.webshopapplication.model.entities.ProductEntity;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowAllProductsResponse {
    private Page<ProductEntity> products;
    private Map<Integer, String> images;
}
