package com.ajax.shop.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 05.05.18
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Goods {
    @Id
    @GeneratedValue
    private Long id;
    private @NonNull String name;
    @ManyToOne
    private Category category;
}
