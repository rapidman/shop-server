package com.ajax.shop.data;

import com.ajax.shop.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 18.06.18
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryData {
    private Long id;
    @NotBlank
    private String name;

    public CategoryData(Category entity) {
        setId(entity.getId());
        setName(entity.getName());
    }
}
