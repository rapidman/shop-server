package com.ajax.shop.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 31.08.18
 */
@Data
@NoArgsConstructor
public class ValidationErrorResponse {
    private String message;

    public ValidationErrorResponse(String message) {
        this.message = message;
    }
}
