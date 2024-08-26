package com.example.springwebcar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springwebcar.pojo.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car c ORDER BY c.sellingPrice DESC")
    List<Car> findTopNCarsOrderedByPriceDesc(Pageable pageable);
}
