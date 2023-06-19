package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etf.webshopapplication.model.entities.ImageEntity;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    @Query("select i from ImageEntity i where i.productId=:productId")
    List<ImageEntity> findAllByProductId(@Param("productId") int productId);

    @Modifying
    @Transactional
    void deleteByProductId(int productId);

    @Query("select i.path from ImageEntity i where i.productId=:productId and i.id=(select max(f.id) from ImageEntity f where f.productId=:productId)")
    String findOneByProductId(@Param("productId") int productId);

}
