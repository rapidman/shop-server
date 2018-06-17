package com.ajax.shop.service;

import com.ajax.shop.config.TestDBConfig;
import com.ajax.shop.data.GoodsSearchCriteria;
import com.ajax.shop.entity.Category;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.repository.CategoryRepository;
import com.ajax.shop.repository.CategoryRepositoryIT;
import com.ajax.shop.repository.GoodsRepository;
import com.ajax.shop.service.impl.DataServiceImpl;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 17.06.18
 */
@SpringBootTest(classes = {DataServiceIT.TestConfiguration.class, TestDBConfig.class})
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:test.properties")
public class DataServiceIT {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private DataService dataService;

    @Test
    public void test() {
        //given:
        Category category = CategoryRepositoryIT.createCategory();
        categoryRepository.save(category);

        Goods goods = new Goods();
        goods.setName("goods name");
        goods.setCategory(category);
        category.setGoodsList(Arrays.asList(goods));
        goodsRepository.save(goods);
        GoodsSearchCriteria searchCriteria = new GoodsSearchCriteria().withCategoryId(category.getId());

        //when:
        Page<Goods> result = dataService.findGoods(searchCriteria, PageRequest.of(0, 10));

        //then:
        assertEquals(1, result.getTotalElements());
    }

    @Configuration
    @EntityScan(basePackages = {"com.ajax.shop.entity"})
    @EnableJpaRepositories(basePackages = {"com.ajax.shop.repository"})
    @PropertySources({
            @PropertySource("classpath:test.properties")

    })
    static class TestConfiguration {

        @Bean
        DataService dataService(){
            return new DataServiceImpl();
        }

//        @Autowired
//        void mbeanExporter(AnnotationMBeanExporter mBeanExporter) {
//            mBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
//        }
    }

}
