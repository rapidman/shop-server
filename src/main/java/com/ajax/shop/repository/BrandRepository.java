package com.ajax.shop.repository;

import com.ajax.shop.entity.Brand;
import com.ajax.shop.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 05.05.18
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, QuerydslPredicateExecutor<Goods> {
}
