package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.classes.Order;


public interface OrderRepository extends CrudRepository<Order, Long> {

}
