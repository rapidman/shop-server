package com.ajax.shop.service.impl;

import com.ajax.shop.data.CategoriesSearchCriteria;
import com.ajax.shop.data.CreateCategoryRequest;
import com.ajax.shop.data.EditCategoryRequest;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.entity.Category;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.exception.ValidationException;
import com.ajax.shop.repository.CategoryRepository;
import com.ajax.shop.repository.GoodsRepository;
import com.ajax.shop.repository.spec.CategorySpecifications;
import com.ajax.shop.repository.spec.GoodsSpecifications;
import com.ajax.shop.service.DataService;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 07.06.18
 */
@Service
public class DataServiceImpl implements DataService {
    private static final Logger log = LoggerFactory.getLogger(DataServiceImpl.class);
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        Page<Category> result = categoryRepository.findAll(pageable);
        for (Category category : result) {
            category.setGoodsSize(category.getGoodsList().size());
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Goods> findGoods(GoodsSearchCriteria criteria, Pageable pageable) {
        return goodsRepository.findAll(GoodsSpecifications.goodsBelongsToCategoryOrHasName(criteria.getCategoryId(), criteria.getName()), pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findCategories(CategoriesSearchCriteria criteria, Pageable pageable) {
        Predicate predicate = CategorySpecifications.goodsBelongsToCategoryOrBrand(criteria.getQuery());
        return categoryRepository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Goods findGoodsById(Long goodsId) {
        return goodsRepository.getOne(goodsId);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findCategoryById(Long catId) {
        return categoryRepository.getOne(catId);
    }

    @Transactional
    @Override
    public void save(Goods goods) {
        goodsRepository.save(goods);
    }

    @Transactional(readOnly = true)
    @Override
    public int findMaxViewCount() {
        return goodsRepository.findMaxViewCount();
    }

    @Transactional
    @Override
    public Category createCategory(CreateCategoryRequest request) {
        checkUniqName(request.getName());
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }

    private void checkUniqName(@NotBlank String name) {
        Optional<Category> existsCategory = categoryRepository.findByName(name);
        if(existsCategory.isPresent()){
            throw new ValidationException("Категория с наименованием '" + name + "' уже существует!");
        }
    }

    @Override
    public void updateCategory(EditCategoryRequest request) {
        checkUniqName(request.getName());
        Category category = categoryRepository.getOne(request.getId());
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long catId) {
        try {
            Category category = categoryRepository.getOne(catId);
            for (Goods goods : category.getGoodsList()) {
                goodsRepository.deleteById(goods.getId());
            }
            categoryRepository.delete(category);
        } catch (EntityNotFoundException e) {
            log.warn("Can't find by id=" + catId, e);
        }
    }
}
