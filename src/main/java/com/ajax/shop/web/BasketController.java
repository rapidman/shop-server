package com.ajax.shop.web;

import com.ajax.shop.Constants;
import com.ajax.shop.data.BasketData;
import com.ajax.shop.data.OrderData;
import com.ajax.shop.data.UserOrderData;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.service.DataService;
import com.ajax.shop.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 12.05.18
 */
@RestController
@RequestMapping("/api/v1" + Constants.BASKET_URI)
public class BasketController {
    private static final Logger log = LoggerFactory.getLogger(BasketController.class);
    public static final String BASKET_ATTR = "BASKET_ATTR";
    @Autowired
    private DataService dataService;
    @Value("${baseUrl}")
    private String baseUrl;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private EmailService emailService;

    @GetMapping
    @CrossOrigin(origins = {"https://quiet-fjord-85272.herokuapp.com", "http://localhost:4200"})
    public BasketData getBasket() {
        return (BasketData) httpSession.getAttribute(BASKET_ATTR);
    }

    @PutMapping
    @CrossOrigin(origins = {"https://quiet-fjord-85272.herokuapp.com", "http://localhost:4200"})
    public void putOrder(@Valid @RequestBody OrderData orderData) {
        BasketData basket = getBasket();
        Goods goods = dataService.findGoodsById(orderData.getProductId());
        orderData.setGoodsDescription(goods.getDescription());
        orderData.setGoodsName(goods.getName());
        orderData.setCategoryId(goods.getCategory().getId());
        orderData.setPrice(goods.getPrice());
        basket.getOrders().add(orderData);
        httpSession.setAttribute(BASKET_ATTR, basket);
    }

    @DeleteMapping("/{goodsId}")
    @CrossOrigin(origins = {"https://quiet-fjord-85272.herokuapp.com", "http://localhost:4200"})
    public void deleteOrder(@PathVariable("goodsId") Long goodsId) {
        BasketData basket = getBasket();
        basket.getOrders().removeIf(item -> item.getProductId().equals(goodsId));
        httpSession.setAttribute(BASKET_ATTR, basket);
    }

    @PostMapping("/order")
    public void makeOrder(@RequestBody @Valid UserOrderData userOrderData) {
        log.info(userOrderData.toString());
        BasketData basket = getBasket();
        emailService.sendEmail(userOrderData);
        basket.getOrders().clear();
        httpSession.setAttribute(BASKET_ATTR, basket);
    }
}
