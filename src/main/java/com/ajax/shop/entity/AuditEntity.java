package com.ajax.shop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 28.08.18
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {
    public static final String CREATED_AT_FIELD = "created_at";
    public static final String LAST_MODIFIED_AT_FIELD = "last_modified_at";

    @CreatedDate
    @Column(name = CREATED_AT_FIELD)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = LAST_MODIFIED_AT_FIELD)
    private Date lastModifiedAt;

}