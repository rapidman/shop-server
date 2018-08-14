package com.ajax.shop.service;

import com.ajax.shop.data.OrderData;
import com.ajax.shop.data.UserOrderData;
import com.ajax.shop.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.08.18
 */
@Service
public class TemplateService {
    public static final String EMAIL_ORDER_TEMPLATE = "EMAIL_ORDER_TEMPLATE";
    private TemplateEngine templateEngine;
    private TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new SpringTemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
    }

    public String processTemplate(Map<String, Object> params, String templateKey){
        Context ctx = new Context();
        ctx.setVariables(new HashMap(params));
        String content = templateRepository.findByKey(templateKey).getContent();
        return this.templateEngine.process(content, ctx);
    }

    public String processEmailOrderTemplate(UserOrderData userOrderData){
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userOrderData.getName() + " " + userOrderData.getLastName());
        params.put("products", userOrderData.getOrders());
        return processTemplate(params, EMAIL_ORDER_TEMPLATE);
    }

}
