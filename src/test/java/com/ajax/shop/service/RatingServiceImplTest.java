package com.ajax.shop.service;

import com.ajax.shop.service.impl.RatingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 20.07.18
 */
@RunWith(MockitoJUnitRunner.class)
public class RatingServiceImplTest {
    @InjectMocks
    private RatingServiceImpl ratingService;

    @Test
    public void test1(){
        int maxViewCount = 150;
        int currProductViewCount = 20;

        int stars = ratingService.getRatingStars(maxViewCount, currProductViewCount);
        assertEquals(1, stars);
    }

    @Test
    public void test2(){
        int maxViewCount = 150;
        int currProductViewCount = 100;

        int stars = ratingService.getRatingStars(maxViewCount, currProductViewCount);
        assertEquals(4, stars);
    }

    @Test
    public void test3(){
        int maxViewCount = 150;
        int currProductViewCount = 0;

        int stars = ratingService.getRatingStars(maxViewCount, currProductViewCount);
        assertEquals(1, stars);
    }
}
