package com.ajax.shop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Car extends AuditEntity {
    @Id
    @GeneratedValue
    private Long id;
    private @NonNull
    String name;
}