package com.example.springwebcar.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

import jakarta.persistence.GenerationType;

@Component
@Entity
@Table(name = "CarDetails")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "carId")
	private int carId;
	@Column(name = "carName")
	private String carName;
	@Column(name = "year")
	private long year;
	@Column(name = "sellingPrice")
	long sellingPrice;
	@Column(name = "kmDriven")
	private long kmDriven;
	@Column(name = "fuel")
	private String fuel;
	@Column(name = "sellerType")
	private String sellerType;
	@Column(name = "transmission")
	private String transmission;
	@Column(name = "owner")
	private String owner;

	public Car() {
	}

	public Car(int carId, String carName, long year, long sellingPrice, long kmDriven, String fuel, String sellerType,
			String transmission, String owner) {
		this.carId = carId;
		this.carName = carName;
		this.year = year;
		this.sellingPrice = sellingPrice;
		this.kmDriven = kmDriven;
		this.fuel = fuel;
		this.sellerType = sellerType;
		this.transmission = transmission;
		this.owner = owner;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public long getyear() {
		return year;
	}

	public void setyear(long year) {
		this.year = year;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public long getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public long getKmDriven() {
		return kmDriven;
	}

	public void setKmDriven(long kmDriven) {
		this.kmDriven = kmDriven;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
