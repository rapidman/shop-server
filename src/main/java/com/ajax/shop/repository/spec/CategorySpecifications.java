package com.ajax.shop.repository.spec;

import com.ajax.shop.entity.QCategory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 23.06.18
 */
public class CategorySpecifications {
    public static Predicate goodsBelongsToCategoryOrBrand(String query) {
        if (query == null) return null;
        BooleanExpression categoryPredicate = QCategory.category.goodsList.any().name.containsIgnoreCase(query);
        BooleanExpression brandPredicate = QCategory.category.goodsList.any().brand.name.containsIgnoreCase(query);
        return categoryPredicate.or(brandPredicate);
    }

}
