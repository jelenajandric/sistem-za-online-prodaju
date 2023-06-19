package org.unibl.etf.webshopapplication.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.unibl.etf.webshopapplication.model.entities.AttributeValuesEntity;
import org.unibl.etf.webshopapplication.model.entities.AttributeValuesEntityPK;
import org.unibl.etf.webshopapplication.model.entities.QAttributeValuesEntity;

import java.util.List;

@Repository
public interface AttributeValuesRepository extends JpaRepository<AttributeValuesEntity, AttributeValuesEntityPK>,
        QuerydslPredicateExecutor<AttributeValuesEntity>, QuerydslBinderCustomizer<QAttributeValuesEntity> {

    @Override
    default void customize(
            QuerydslBindings bindings, QAttributeValuesEntity root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    @Query("select a from AttributeValuesEntity a where a.productId=:productId")
    List<AttributeValuesEntity> getAttributeValuesEntitiesByProductId(@PathVariable("productId") int productId);

    @Modifying
    @Transactional
    @Query("delete from AttributeValuesEntity a where a.productId=:productId")
    void deleteByProductId(@PathVariable("productId") int productId);

    @Query("select distinct av.productId from AttributeValuesEntity av where av.attributeId=:id and av.value=:value")
    List<Integer> findAllDistinctProductIdsWithCondition(@PathVariable("id") int id, @PathVariable("value") String value);

}
