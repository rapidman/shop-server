package com.ajax.shop.repository;

import com.ajax.shop.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 05.05.18
 */
@RepositoryRestResource(collectionResourceRel = "goods", path = "goods")
@CrossOrigin(origins = "http://localhost:4200")
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Page<Goods> findByCategoryNameIgnoreCaseContainingOrderByName(@Param("categoryName") String categoryName, Pageable pageable);
}
