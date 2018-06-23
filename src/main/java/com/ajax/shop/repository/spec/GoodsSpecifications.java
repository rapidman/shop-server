package com.ajax.shop.repository.spec;

import com.ajax.shop.entity.QGoods;
import com.querydsl.core.types.Predicate;


/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 17.06.18
 */
public class GoodsSpecifications {
    public static Predicate goodsBelongsToCategory(Long categoryId) {
        return categoryId != null ? QGoods.goods.category.id.eq(categoryId) : null;
    }
}
