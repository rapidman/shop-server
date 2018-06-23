package com.ajax.shop.service.impl;

import com.ajax.shop.data.CategoriesSearchCriteria;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.entity.Category;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.repository.CategoryRepository;
import com.ajax.shop.repository.GoodsRepository;
import com.ajax.shop.repository.spec.CategorySpecifications;
import com.ajax.shop.repository.spec.GoodsSpecifications;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 07.06.18
 */
@Service
public class DataServiceImpl implements DataService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Category> getAllCategories(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Goods> findGoods(GoodsSearchCriteria searchCriteria, Pageable pageable){
        return goodsRepository.findAll(GoodsSpecifications.goodsBelongsToCategory(searchCriteria.getCategoryId()), pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findCategories(CategoriesSearchCriteria searchCriteria, Pageable pageable) {
        return categoryRepository.findAll(CategorySpecifications.goodsBelongsToCategory(searchCriteria.getQuery()), pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Goods findGoodsById(Long goodsId) {
        return goodsRepository.getOne(goodsId);
    }
}
