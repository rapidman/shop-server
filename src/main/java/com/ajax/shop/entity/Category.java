package com.ajax.shop.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 11.05.18
 */
@Entity
@Table(name = "category", schema = "ajax")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Category {
    @Id
    @SequenceGenerator(sequenceName = "ajax.category_id_seq", name = "categoryIdSequence", allocationSize = 1)
    @GeneratedValue(generator = "categoryIdSequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Goods> goodsList;
}
