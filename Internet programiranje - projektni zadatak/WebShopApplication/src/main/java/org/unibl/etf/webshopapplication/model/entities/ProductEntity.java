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
@Table(name = "product", schema = "webshop_app")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "is_new")
    private boolean isNew;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "contact")
    private String contact;
    @Basic
    @Column(name = "is_sold")
    private boolean isSold;
    @Basic
    @Column(name = "category_id")
    private int categoryId;
    @Basic
    @Column(name = "client_id_selling")
    private int clientIdSelling;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

}
