package com.ajax.shop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 24.06.18
 */
@Entity
@Table(name = "brand", schema = "ajax")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Brand extends AuditEntity {
    @Id
    @SequenceGenerator(sequenceName = "ajax.brand_id_seq", name = "brandIdSequence", allocationSize = 1)
    @GeneratedValue(generator = "brandIdSequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String name;

}
