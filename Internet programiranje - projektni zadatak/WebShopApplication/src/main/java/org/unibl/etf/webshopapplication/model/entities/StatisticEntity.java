package org.unibl.etf.webshopapplication.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistic", schema = "webshop_app")
public class StatisticEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "client_username")
    private String clientUsername;
    @Basic
    @Column(name = "what_is_happened")
    private String whatIsHappened;
    @Basic
    @Column(name = "returned_id")
    private int returnedId;
    @Basic
    @Column(name = "date")
    private LocalDateTime date;
}
