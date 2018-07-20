package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.07.18
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderData {
    @NotNull
    private Long productId;
    private Long categoryId;
    @NotNull
    private Integer count;
    private String goodsName;
    private String goodsDescription;
    private Long price;
}
