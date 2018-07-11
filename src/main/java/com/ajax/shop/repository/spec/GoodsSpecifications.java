package com.ajax.shop.repository.spec;

import com.ajax.shop.entity.QGoods;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;


/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 17.06.18
 */
public class GoodsSpecifications {
    public static Predicate goodsBelongsToCategoryOrHasName(Long categoryId, String name) {
        BooleanBuilder predicate = new BooleanBuilder();
        if (categoryId != null) {
            predicate.or(QGoods.goods.category.id.eq(categoryId));
        }
        if (!StringUtils.isEmpty(name)) {
            predicate.or(QGoods.goods.name.containsIgnoreCase(name));
        }
        return predicate;
    }

}
