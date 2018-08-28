package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.data.GoodsData;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.data.Option;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.service.DataService;
import com.ajax.shop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1")
public class GoodsController {
    @Autowired
    private DataService dataService;
    @Autowired
    private RatingService ratingService;

    @GetMapping(Constants.GOODS_URI)
    @CrossOrigin(origins = {"*"})
    public PageImpl<Option> findGoods(@RequestParam(required = false) Long categoryId,
                                      Pageable pageable) {
        Page<Goods> goods = dataService.findGoods(new GoodsSearchCriteria().withCategoryId(categoryId), pageable);
        List<Option> options = goods.stream().map(g -> SearchAutocompleteController.createOption(g.getCategory(), g))
                .collect(Collectors.toList());
        return new PageImpl<>(options, pageable, goods.getTotalElements());
    }

    @GetMapping(Constants.GOODS_URI + "/{goodsId}")
    @CrossOrigin(origins = {"*"})
    public GoodsData findGoodsById(@PathVariable("goodsId") Long goodsId) {
        Goods entity = dataService.findGoodsById(goodsId);
        return new GoodsData(entity);
    }

    @PostMapping(Constants.GOODS_URI + "/rate/{goodsId}")
    public void addRate(@PathVariable("goodsId") Long goodsId) {
        Goods entity = dataService.findGoodsById(goodsId);
        entity.setViewCount(entity.getViewCount() + 1);
        int maxViewCount = dataService.findMaxViewCount();
        entity.setRate(ratingService.getRatingStars(maxViewCount, entity.getRate()));
        dataService.save(entity);
    }
}
