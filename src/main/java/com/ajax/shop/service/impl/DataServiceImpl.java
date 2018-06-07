package com.ajax.shop.service.impl;

import com.ajax.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 07.06.18
 */
@Service
public class DataServiceImpl {
    @Autowired
    private CategoryRepository categoryRepository;
}
