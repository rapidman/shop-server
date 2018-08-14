package com.ajax.shop.repository;

import com.ajax.shop.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.08.18
 */
@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    Template findByKey(String key);
}
