package com.ajax.shop.repository;

import com.ajax.shop.config.TestDBConfig;
import com.ajax.shop.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 07.06.18
 */
@SpringBootTest(classes = {CategoryRepositoryIT.TestConfiguration.class, TestDBConfig.class})
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:test.properties")
public class CategoryRepositoryIT {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCrud() {
        //given:
        Category category = createCategory();

        //when:
        categoryRepository.save(category);

        //then:
        assertEquals(category, categoryRepository.findById(category.getId()).get());
        assertEquals(category,
                categoryRepository
                        .findByNameIgnoreCaseContainingOrderByName("test name", PageRequest.of(0, 10))
                        .iterator()
                        .next());

        //and
        categoryRepository.deleteById(category.getId());
        assertFalse(categoryRepository.findById(category.getId()).isPresent());
    }

    public static Category createCategory() {
        Category category = new Category();
        category.setName("test name");
        return category;
    }


    @Configuration
    @EntityScan(basePackages = {"com.ajax.shop.entity"})
    @EnableJpaRepositories(basePackages = {"com.ajax.shop.repository"})
    @PropertySources({
            @PropertySource("classpath:test.properties")

    })
    static class TestConfiguration {
    }
}
