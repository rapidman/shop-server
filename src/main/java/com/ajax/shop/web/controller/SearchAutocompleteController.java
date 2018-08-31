package com.ajax.shop.web.controller;

import com.ajax.shop.data.CategoriesSearchCriteria;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.data.Group;
import com.ajax.shop.data.Option;
import com.ajax.shop.entity.Category;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1/search/autocomplete")
public class SearchAutocompleteController {
    @Autowired
    private DataService dataService;
    @Value("${baseUrl}")
    private String baseUrl;


    @GetMapping
    @CrossOrigin(origins = {"*"})
    public Page<Group> findByQuery(@RequestParam String query,
                                   Pageable pageable) {
        Page<Category> categories = dataService.findCategories(new CategoriesSearchCriteria().withQuery(query), PageRequest.of(0, 100));
        List<Option> categoryOptions = categories.stream().map(c -> createOption(c, null))
                .collect(Collectors.toList());
        Page<Goods> goods = dataService.findGoods(new GoodsSearchCriteria().withName(query), pageable);
        List<Option> goodsOptions = goods.stream().map(g -> createOption(g.getCategory(), g))
                .collect(Collectors.toList());
        return new PageImpl<>(Arrays.asList(
                new Group(Group.GroupType.CATEGORY, categoryOptions),
                new Group(Group.GroupType.PRODUCT, goodsOptions)), pageable, goods.getTotalElements());
    }

    public static Option createOption(Category category, Goods goods) {
        Option option = new Option();
        option.setCategoryId(category.getId());
        if (goods != null) {
            option.setProductId(goods.getId());
            option.setType(Group.GroupType.PRODUCT);
            option.setName(goods.getName());
            option.setDescription(goods.getDescription());
            option.setPrice(goods.getPrice());
        } else {
            option.setType(Group.GroupType.CATEGORY);
            option.setName(category.getName());
        }
        return option;
    }

}
