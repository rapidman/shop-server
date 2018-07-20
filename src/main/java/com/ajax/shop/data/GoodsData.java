package com.ajax.shop.data;

import com.ajax.shop.entity.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 18.06.18
 */
@Getter
@Setter
@NoArgsConstructor
public class GoodsData {
    private Long id;
    private String name;
    private String description;
    private boolean present;

    public GoodsData(Goods entity) {
        setId(entity.getId());
        setName(entity.getName());
        setDescription(entity.getDescription());
        setPresent(entity.isPresent());
    }
}
