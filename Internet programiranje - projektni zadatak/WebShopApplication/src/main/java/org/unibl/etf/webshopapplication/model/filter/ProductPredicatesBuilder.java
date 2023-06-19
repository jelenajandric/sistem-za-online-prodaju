package org.unibl.etf.webshopapplication.model.filter;

import java.util.ArrayList;
import java.util.List;

public class ProductPredicatesBuilder {
    private List<SearchCriteria> params;

    public ProductPredicatesBuilder() {
        params = new ArrayList<>();
    }

    public ProductPredicatesBuilder with(
            String key, String operation, Object value) {

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
}
