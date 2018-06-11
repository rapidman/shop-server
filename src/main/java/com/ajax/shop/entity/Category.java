package com.ajax.shop.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;

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
@ToString(exclude = {"goodsList"})
@EqualsAndHashCode
public class Category {
    @Id
    @SequenceGenerator(sequenceName = "ajax.category_id_seq", name = "categoryIdSequence", allocationSize = 1)
    @GeneratedValue(generator = "categoryIdSequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String name;
    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Goods> goodsList;
}
