package com.ajax.shop.service.impl;

import com.ajax.shop.entity.Category;
import com.ajax.shop.repository.CategoryRepository;
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

    @Transactional(readOnly = true)
    @Override
    public Page<Category> getAllCategories(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }
}
