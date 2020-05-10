package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.classes.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
