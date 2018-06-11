package com.ajax.shop.service;

import com.ajax.shop.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 07.06.18
 */
public interface DataService {
    @Transactional(readOnly = true)
    Page<Category> getAllCategories(Pageable pageable);
}
