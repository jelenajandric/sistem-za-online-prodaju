package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unibl.etf.webshopapplication.model.entities.AttributeEntity;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {

    @Query("select a.name from AttributeEntity a where a.categoryId =:categoryId ")
    List<String> getAllAttributesByCategoryId(int categoryId);

    List<AttributeEntity> findAttributeEntitiesByCategoryId(int categroyId);

    @Query("select a.name from AttributeEntity a where a.id=:id")
    String getAttributeName(int id);


}
