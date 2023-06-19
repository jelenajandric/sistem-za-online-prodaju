package org.unibl.etf.webshopapplication.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buying", schema = "webshop_app")
public class BuyingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "client_id")
    private int clientId;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "payment_method")
    private String paymentMethod;
    @Basic
    @Column(name = "buying_date")
    private Date buyingDate;
}
