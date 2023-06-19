package org.unibl.etf.webshopapplication.model.filter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.unibl.etf.webshopapplication.model.entities.ProductEntity;

import static org.springframework.jdbc.support.JdbcUtils.isNumeric;

public class ProductPredicate {
    private SearchCriteria criteria;

    public BooleanExpression getPredicate() {
        PathBuilder<ProductEntity> entityPath = new PathBuilder<>(ProductEntity.class, "product");

        if (isNumeric(Integer.parseInt(criteria.getValue().toString()))) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        } else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }
}
