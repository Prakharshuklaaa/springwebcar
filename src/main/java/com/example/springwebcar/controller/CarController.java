package com.example.springwebcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springwebcar.pojo.Car;
import com.example.springwebcar.service.CarService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    public CarController() {
    }

    @GetMapping("/load-cars")
    public String getMethodName(@RequestParam String csvFilePath) {
        carService.loadCsvData(csvFilePath);
        return new String();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable int id) {
        return carService.getCarId(id);
    }

    @PatchMapping("/carsupdate/{id}")
    public ResponseEntity<Car> getMethodName(@PathVariable int id, @RequestBody Car car) {
        System.out.println("-----");
        System.out.println(car.getCarName() + car.getyear());
        Car existingCar = carService.getCarId(id);

        if (existingCar != null) {

            existingCar.setyear(car.getyear());

            Car updatedCar = carService.updateCar(existingCar);

            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/cardelete/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable int id) {
        boolean isRemoved = carService.deleteCarById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/allcars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();

        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }

    }

    @GetMapping("/ordered-by-price")
    public ResponseEntity<List<Car>> findTopNCarsOrderedByPriceDesc(@RequestParam int limit) {
        List<Car> cars = carService.findTopNCarsOrderedByPriceDesc(limit);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
    }

}
