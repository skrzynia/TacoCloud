package com.example.demo.repos;

import com.example.demo.classes.Taco;

public interface JDBCTacoRepository {

	public Taco save(Taco taco);
}
