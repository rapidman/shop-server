package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.entity.Category;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api")
public class CategoriesController {
    @Autowired
    private DataService dataService;
    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping("/categories")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<Category> findGoods(Pageable pageable){
        Page<Category> result = dataService.getAllCategories(pageable);
        for (Category category : result) {
            category.setLink(baseUrl + Constants.GOODS_URI);
        }
        return result;
    }
}
