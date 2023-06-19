package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etf.webshopapplication.model.entities.BuyingEntity;

@Repository
public interface BuyingRepository extends JpaRepository<BuyingEntity, Integer> {

    @Modifying
    @Transactional
    @Query("delete from BuyingEntity b where b.productId=:productId")
    void deleteBuyingByProductId(@Param("productId") int productId);

    @Modifying
    @Transactional
    @Query("delete from BuyingEntity b where b.clientId=:clientId")
    void deleteBuyingByClientId(@Param("clientId") int clientId);
}
