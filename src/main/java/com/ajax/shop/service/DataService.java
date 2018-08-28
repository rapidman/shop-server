package com.ajax.shop.service;

import com.ajax.shop.data.CreateCategoryRequest;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.data.CategoriesSearchCriteria;
import com.ajax.shop.entity.Category;
import com.ajax.shop.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 07.06.18
 */
public interface DataService {

    Page<Category> getAllCategories(Pageable pageable);

    Page<Goods> findGoods(GoodsSearchCriteria searchCriteria, Pageable pageable);

    Page<Category> findCategories(CategoriesSearchCriteria searchCriteria, Pageable pageable);

    Goods findGoodsById(Long goodsId);

    Category findCategoryById(Long catId);

    void save(Goods goods);

    int findMaxViewCount();

    Category createCategory(CreateCategoryRequest request);
}
