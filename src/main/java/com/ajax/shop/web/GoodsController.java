package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1")
public class GoodsController {
    @Autowired
    private DataService dataService;

    @GetMapping(Constants.GOODS_URI)
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<Goods> findGoods(@RequestParam(required = false) Long categoryId,
                                 Pageable pageable) {
        return dataService.findGoods(new GoodsSearchCriteria().withCategoryId(categoryId), pageable);
    }
}
