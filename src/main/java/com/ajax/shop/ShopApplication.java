package com.ajax.shop;

import com.ajax.shop.entity.Car;
import com.ajax.shop.entity.Category;
import com.ajax.shop.entity.Goods;
import com.ajax.shop.repository.CarRepository;
import com.ajax.shop.repository.CategoryRepository;
import com.ajax.shop.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository carRepository,
                           GoodsRepository goodsRepository,
                           CategoryRepository categoryRepository) {
        return args -> {
//            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
//                    "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
//                Car car = new Car();
//                car.setName(name);
//                carRepository.save(car);
//            });
//            carRepository.findAll().forEach(System.out::println);
            Stream.of("Джинсы", "Рубашки", "Подушки", "Матрацы", "Носки").forEach(name -> {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
                List<Goods> goodsList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Goods goods = new Goods();
                    goods.setCategory(category);
                    goods.setName(name + i);
                    goodsList.add(goods);
                    goodsRepository.save(goods);
                }
                category.setGoodsList(goodsList);
            });
        };
    }

}
