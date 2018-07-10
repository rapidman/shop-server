package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 10.07.18
 */
@Getter
@Setter
@NoArgsConstructor
public class Group {
    private GroupType type;
    private List<Option> options;

    public Group(GroupType type, List<Option> options) {
        this.type = type;
        this.options = options;
    }

    public enum GroupType{
        CATEGORY, PRODUCT
    }
}
