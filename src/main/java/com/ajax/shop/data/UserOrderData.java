package com.ajax.shop.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 06.08.18
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserOrderData {
    @NotNull
    private List<OrderData> orders;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
}
