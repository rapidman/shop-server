package com.ajax.shop.service;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 20.07.18
 */
public interface RatingService {
    int getRatingStars(int maxViewCount, int currProductViewCount);
}
