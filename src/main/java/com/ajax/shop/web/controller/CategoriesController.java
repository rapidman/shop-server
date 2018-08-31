package com.ajax.shop.web.controller;

import com.ajax.shop.Constants;
import com.ajax.shop.data.*;
import com.ajax.shop.entity.Category;
import com.ajax.shop.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1")
@Valid
@CrossOrigin(origins = {"*"})
public class CategoriesController {
    private static final Logger log = LoggerFactory.getLogger(CategoriesController.class);
    @Autowired
    private DataService dataService;
    @Value("${baseUrl}")
    private String baseUrl;

    @GetMapping(Constants.CATEGORIES_URI)
    public Page<Category> findAllCategories(Pageable pageable) {
        Page<Category> result = dataService.getAllCategories(pageable);
        for (Category category : result) {
            category.setLink(baseUrl + Constants.GOODS_URI);
        }
        return result;
    }

    @GetMapping(Constants.SEARCH_CATEGORIES_URI)
    public Page<Group> findCategoriesByQuery(@RequestParam String query,
                                             Pageable pageable) {
        Page<Category> categories = dataService.findCategories(new CategoriesSearchCriteria().withQuery(query), pageable);
        List<Option> options = categories.stream().map(c -> SearchAutocompleteController.createOption(c, null)).collect(Collectors.toList());
        return new PageImpl<>(Arrays.asList(new Group(Group.GroupType.CATEGORY, options)));
    }

    @GetMapping(Constants.CATEGORIES_URI + "/{catId}")
    public CategoryData findCategoryById(@PathVariable("catId") Long catId) {
        Category entity = dataService.findCategoryById(catId);
        return new CategoryData(entity);
    }

    @PostMapping(Constants.CATEGORIES_URI)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryData create(@RequestBody CreateCategoryRequest request) {
        log.info("create {}", request);
        Category category = dataService.createCategory(request);
        return new CategoryData(category);
    }

    @DeleteMapping(Constants.CATEGORIES_URI + "/{catId}")
    public void deleteCategory(@PathVariable("catId") Long catId){
        dataService.deleteCategoryById(catId);
    }
}
