package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 10.07.18
 */
@Getter
@Setter
@NoArgsConstructor
public class Option {
    private Group.GroupType type;
    private String name;
    private Long productId;
    private Long categoryId;

    public Option(Group.GroupType type, String name, Long productId, Long categoryId) {
        this.type = type;
        this.name = name;
        this.productId = productId;
        this.categoryId = categoryId;
    }
}
