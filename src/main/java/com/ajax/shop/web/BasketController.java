package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.data.*;
import com.ajax.shop.entity.Category;
import com.ajax.shop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1" + Constants.BASKET_URI)
public class BasketController {
    public static final String BASKET_ATTR = "BASKET_ATTR";
    @Autowired
    private DataService dataService;
    @Value("${baseUrl}")
    private String baseUrl;
    @Autowired
    private HttpSession httpSession;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public BasketData getBasket(){
        return (BasketData) httpSession.getAttribute(BASKET_ATTR);
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public void putOrder(@Valid @RequestBody OrderData orderData){
        BasketData basket = getBasket();
        basket.getOrders().add(orderData);
        httpSession.setAttribute(BASKET_ATTR, basket);
    }
}
