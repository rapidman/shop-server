package com.ajax.shop.web;

import com.ajax.shop.entity.Goods;
import com.ajax.shop.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/goods")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<Goods> findGoods(Pageable pageable){
        return goodsRepository.findAll(pageable);
    }
}
