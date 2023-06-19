package org.unibl.etf.webshopapplication.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValuesEntityPK implements Serializable {
    @Column(name = "attribute_id")
    @Id
    private Integer attributeId;
    @Column(name = "product_id")
    @Id
    private Integer productId;

}
