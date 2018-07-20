package com.ajax.shop.service.impl;

import com.ajax.shop.service.RatingService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 20.07.18
 */
@Service
public class RatingServiceImpl implements RatingService {
    public static final int MAX_STARS = 5;
    public static final int ONE_STAR_AMOUNT = 100 / MAX_STARS;
    private static final int MIN_STARS = 1;

    @Override
    public int getRatingStars(int maxViewCount, int currProductViewCount) {
        if (maxViewCount == 0 || currProductViewCount == 0) return MIN_STARS;
        float val = ((float) maxViewCount / (float) 100);
        int percents = (int) (currProductViewCount / val);
        for (int i = 1; i <= MAX_STARS; i++) {
            if (i * ONE_STAR_AMOUNT >= percents) {
                return i;
            }
        }
        return MAX_STARS;
    }
}
