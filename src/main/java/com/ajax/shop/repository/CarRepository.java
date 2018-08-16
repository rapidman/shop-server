package com.ajax.shop.repository;

import com.ajax.shop.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = {"https://quiet-fjord-85272.herokuapp.com", "http://localhost:4200"})
public interface CarRepository extends JpaRepository<Car, Long> {
}