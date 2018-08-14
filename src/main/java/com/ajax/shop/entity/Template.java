package com.ajax.shop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.08.18
 */
@Entity
@Table(name = "template", schema = "ajax")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Template {
    @Id
    @SequenceGenerator(sequenceName = "ajax.template_id_seq", name = "templateIdSequence", allocationSize = 1)
    @GeneratedValue(generator = "templateIdSequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String key;
    @NotBlank
    private String content;

}
