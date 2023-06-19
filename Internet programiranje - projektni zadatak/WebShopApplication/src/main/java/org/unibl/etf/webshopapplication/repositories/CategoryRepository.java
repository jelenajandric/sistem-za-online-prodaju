package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.unibl.etf.webshopapplication.model.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity getCategoryEntityByName(String name);

    @Query("select c.name from CategoryEntity c where c.id =:id")
    String getCategoryNameById(@Param("id") int id);
}
