package com.ajax.shop.service;

import com.ajax.shop.ShopApplication;
import com.ajax.shop.config.TestDBConfig;
import com.ajax.shop.data.OrderData;
import com.ajax.shop.data.UserOrderData;
import com.ajax.shop.repository.TemplateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.ajax.shop.service.TemplateServiceIT.getOrderData;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 14.08.18
 */
@SpringBootTest(classes = {EmailServiceIT.TestConfiguration.class, TestDBConfig.class})
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:test.properties")
public class EmailServiceIT {


    @Autowired
    private TemplateService templateService;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private EmailService emailService;

    @Before
    public void before() throws Exception{
        ShopApplication.initTemplates(templateRepository);
    }


    @Test
    public void test(){
        //given:
        List<OrderData> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(getOrderData(i));
        }
        UserOrderData data = new UserOrderData();
        data.setEmail("test@email.com");
        data.setLastName("lastName");
        data.setName("name");
        data.setOrders(orders);
        data.setPhone("+79095404605");

        emailService.sendEmail(data);
    }


    @Configuration
    @EntityScan(basePackages = {"com.ajax.shop.entity"})
    @EnableJpaRepositories(basePackages = {"com.ajax.shop.repository"})
    @PropertySources({
            @PropertySource("classpath:test.properties")

    })
    static class TestConfiguration {

        @Bean
        EmailService emailService(){
            return new EmailService();
        }

        @Bean
        @Autowired
        TemplateService templateService(TemplateRepository templateRepository) {
            return new TemplateService(templateRepository);
        }
    }

}
