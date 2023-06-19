package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etf.webshopapplication.model.entities.CommentEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findAllByProductId(int productId);

    @Modifying
    @Transactional
    void deleteByClientId(int clientId);

    @Modifying
    @Transactional
    void deleteByProductId(int productId);

    @Modifying
    @Transactional
    void deleteById(int clientId);
}
