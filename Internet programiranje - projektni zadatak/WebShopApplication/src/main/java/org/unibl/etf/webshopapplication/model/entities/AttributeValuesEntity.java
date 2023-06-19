package org.unibl.etf.webshopapplication.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attribute_values", schema = "webshop_app")
@IdClass(AttributeValuesEntityPK.class)
public class AttributeValuesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attribute_id")
    private Integer attributeId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Basic
    @Column(name = "value")
    private String value;

}
