package com.ajax.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 05.05.18
 */
@Entity
@Table(name = "goods", schema = "ajax")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"category"})
@EqualsAndHashCode
public class Goods {
    @Id
    @SequenceGenerator(sequenceName = "ajax.goods_id_seq", name = "goodsIdSequence", allocationSize = 1)
    @GeneratedValue(generator = "goodsIdSequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
