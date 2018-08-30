package com.ajax.shop.data;

import com.ajax.shop.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 18.06.18
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateCategoryRequest {
    @NotBlank
    private String name;

    public CreateCategoryRequest(Category entity) {
        setName(entity.getName());
    }
}
