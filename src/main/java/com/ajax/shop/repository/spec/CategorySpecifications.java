package com.ajax.shop.repository.spec;

import com.ajax.shop.entity.QCategory;
import com.querydsl.core.types.Predicate;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 23.06.18
 */
public class CategorySpecifications {
    public static Predicate goodsBelongsToCategory(String query) {
        return query != null ? QCategory.category.goodsList.any().name.containsIgnoreCase(query) : null;
    }

}
