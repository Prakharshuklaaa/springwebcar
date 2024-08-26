package com.example.springwebcar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springwebcar.pojo.Car;
import com.example.springwebcar.repo.CarRepo;

@SpringBootTest
class SpringwebcarApplicationTests {

	@Autowired
	CarRepo repo;

	@Test
	public void insertTest() {
		Car c1 = new Car(1, "Maruti 800 AC", 2007, 60000, 700000, "Petrol", "Individual", "Manual", "First Owner");
		repo.save(c1);
	}

}
