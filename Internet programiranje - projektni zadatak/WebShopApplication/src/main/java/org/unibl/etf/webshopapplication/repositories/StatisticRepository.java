package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.webshopapplication.model.entities.StatisticEntity;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity, Integer> {
}
