package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.webshopapplication.model.entities.ClientEntity;
import org.unibl.etf.webshopapplication.model.entities.MailToTechnicalSupportEntity;

@Repository
public interface MailToTechnicalSupportRepository extends JpaRepository<MailToTechnicalSupportEntity, Integer> {

}
