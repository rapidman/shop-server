package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 17.06.18
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoriesSearchCriteria {
    private String query;

    public CategoriesSearchCriteria withQuery(String query) {
        setQuery(query);
        return this;
    }
}
