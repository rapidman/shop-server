package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.07.18
 */
@Getter
@Setter
@NoArgsConstructor
public class BasketData {
    private List<OrderData> orders = new ArrayList<>();
}
