package com.example.demo.repos;

import com.example.demo.classes.Order;

public interface JDBCOrderRepository {

	public Order save(Order order);
}
