package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.data.GoodsData;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping(Constants.GOODS_URI + "/{goodsId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public GoodsData findGoodsById(@PathVariable("goodsId") Long goodsId) {
        Goods entity = dataService.findGoodsById(goodsId);
        return new GoodsData(entity.getId(), entity.getName());
    }
}
