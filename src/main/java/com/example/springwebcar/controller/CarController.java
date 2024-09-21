package com.example.springwebcar.controller;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springwebcar.pojo.Car;
import com.example.springwebcar.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    // @PostMapping("/load-car")
    // public ResponseEntity<Map<String, String>> getMethodName(@RequestBody Car
    // car) {
    // System.out.println("Car Name in Controller: "+ car.getCarName());
    // carService.insert(car);
    // Map<String, String> response = new HashMap<>();
    // response.put("message", "Car Listed");
    // return ResponseEntity.ok(response);
    // }

    @PostMapping("/load-car")
    public ResponseEntity<Map<String, String>> loadCar(HttpServletRequest request) throws Exception {
        // Log the raw incoming request body
        String jsonRequest;
        jsonRequest = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines()
                .collect(Collectors.joining("\n"));

        // System.out.println("Raw JSON Request: " + jsonRequest); // This will print the raw JSON sent from Angular

        // Optionally, still try to map it to Car object to see if binding works
        Car car = new ObjectMapper().readValue(jsonRequest, Car.class);
        System.out.println("Car Object after Deserialization: " + car);

        // Continue with your business logic
        carService.insert(car);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Car Listed");
        return ResponseEntity.ok(response);
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
