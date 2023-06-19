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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.unibl.etf.webshopapplication.model.ProductWithPhoto;
import org.unibl.etf.webshopapplication.model.entities.ProductEntity;
import org.unibl.etf.webshopapplication.model.entities.QProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>,
        QuerydslPredicateExecutor<ProductEntity>, QuerydslBinderCustomizer<QProductEntity> {

    @Override
    default void customize(
            QuerydslBindings bindings, QProductEntity root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    @Query("select p.categoryId from ProductEntity p where p.id =:id")
    int findCategoryIdByProductId(@PathVariable("id") int id);

    @Query("SELECT p.categoryId from ProductEntity p where p.id=:id")
    int getCategoryIdByProductId(@PathVariable("id") int id);

    @Modifying
    @Transactional
    @Query("delete from ProductEntity p where p.id=:id")
    void deleteById(@PathVariable("id") int id);

    @Modifying
    @Transactional
    @Query("update ProductEntity p set p.title=:title, p.description=:description, p.price=:price, p.isNew=:isNew, " +
            "p.location=:location, p.contact=:contact, p.categoryId =:categoryId where p.id=:id")
    void updateProductById(@Param("id") int id, @Param("title") String title,
                           @Param("description") String description, @Param("price") double price,
                           @Param("isNew") boolean isNew, @Param("location") String location,
                           @Param("contact") String contact, @Param("categoryId") int categoryId);

    @Modifying
    @Transactional
    @Query("update ProductEntity p set p.isSold=:isSold where p.id=:id")
    void changeProductState(@Param("id") int id, @Param("isSold") boolean isSold);

    @Query("select p.id from ProductEntity p")
    List<Integer> findAllIds();

    @Query("select distinct p.location from ProductEntity p")
    List<String> findAllLocations();

    @Query("select c.username from ProductEntity p join ClientEntity c on p.clientIdSelling=c.id where p.id=:id")
    String findSellerUsername(@Param("id") int id);
}
