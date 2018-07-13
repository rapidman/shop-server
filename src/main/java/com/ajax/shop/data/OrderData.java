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
    @NotNull
    private Integer count;
}
