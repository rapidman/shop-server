package com.ajax.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 11.05.18
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private @NonNull String name;
    @OneToMany(mappedBy = "category")
    private List<Goods> goodsList;
}
