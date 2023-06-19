package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etf.webshopapplication.model.entities.PinEntity;

@Repository
public interface PinRepository extends JpaRepository<PinEntity, Integer> {
    @Modifying
    @Transactional
    @Query("update PinEntity p set p.pin=:pin where p.clientId=:clientId")
    void updatePinValue(@Param("pin") int pin, @Param("clientId") int clientId);

    @Query("select p.pin from PinEntity p where p.clientId=:clientId")
    int findPinByClientId(@Param("clientId") int clientId);

    @Modifying
    @Transactional
    @Query("delete from PinEntity p where p.clientId=:clientId")
    void deletePinEntityByClientId(@Param("clientId") int clientId);
}
