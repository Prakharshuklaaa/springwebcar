package com.example.springwebcar.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springwebcar.pojo.Car;
import com.example.springwebcar.repo.CarRepo;
import com.opencsv.CSVReader;

@Service
public class CarService {
    @Autowired
    CarRepo repo;

    @Autowired
    Car obj;

    public void loadCsvData(String csvFilePath) {
        // String csvFilePath = "path/to/your/CAR_DETAILS_DATA.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            reader.readNext(); // Skip header line

            while ((nextLine = reader.readNext()) != null) {
                Car car = new Car();
                car.setCarName(nextLine[0]);
                car.setyear(Long.parseLong(nextLine[1]));
                car.setSellingPrice(Long.parseLong(nextLine[2]));
                car.setKmDriven(Long.parseLong(nextLine[3]));
                car.setFuel(nextLine[4]);
                car.setSellerType(nextLine[5]);
                car.setTransmission(nextLine[6]);
                car.setOwner(nextLine[7]);

                repo.save(car);
                System.out.println("Data Loaded Succesfully...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(Car temp) {
        System.out.println("Car Name in service: " + temp.getCarName());
        // obj.setCarName(temp.getCarName());
        // obj.setyear(temp.getyear());
        // obj.setSellingPrice(temp.getSellingPrice());
        // obj.setKmDriven(temp.getKmDriven());
        // obj.setFuel(temp.getFuel());
        // obj.setSellerType(temp.getSellerType());
        // obj.setTransmission(temp.getTransmission());
        // obj.setOwner(temp.getOwner());
        Car savedCar = repo.save(temp);
        System.out.println("Car added+ " + savedCar.getCarId());

    }

    public Car getCarId(int id) {
        Optional<Car> temp = repo.findById(id);
        Car car = temp.get();
        System.out.println(car.getCarName());
        return temp.get();
    }

    public Car updateCar(Car car) {
        return repo.save(car);
    }

    public boolean deleteCarById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Car> getAllCars() {
        return repo.findAll();
    }

    public List<Car> findTopNCarsOrderedByPriceDesc(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return repo.findTopNCarsOrderedByPriceDesc(pageable);
    }

}
