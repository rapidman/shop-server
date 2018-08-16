package com.ajax.shop.repository;

import com.ajax.shop.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 05.05.18
 */
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
@CrossOrigin(origins = {"https://quiet-fjord-85272.herokuapp.com", "http://localhost:4200"})
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long>,
        QuerydslPredicateExecutor<Category> {
    Page<Category> findByNameIgnoreCaseContainingOrderByName(@Param("name") String name, Pageable pageable);
}
