package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 18.06.18
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EditCategoryRequest extends CreateCategoryRequest{
    @NotNull
    private Long id;
}
