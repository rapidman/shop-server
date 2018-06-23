package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.data.CategoriesSearchCriteria;
import com.ajax.shop.entity.Category;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1")
public class CategoriesController {
    @Autowired
    private DataService dataService;
    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping(Constants.CATEGORIES_URI)
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<Category> findAllCategories(Pageable pageable){
        Page<Category> result = dataService.getAllCategories(pageable);
        for (Category category : result) {
            category.setLink(baseUrl + Constants.GOODS_URI);
        }
        return result;
    }

    @GetMapping(Constants.SEARCH_CATEGORIES_URI)
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<Category> findCategoriesByQuery(@RequestParam String query,
                                                Pageable pageable) {
        return dataService.findCategories(new CategoriesSearchCriteria().withQuery(query), pageable);
    }
}
