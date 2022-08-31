package com.cabit.Cab_It.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Vehicle
{
    /*
     * Basic POJO class for vehicle
     * */
    private String id;
    private String model;
    private String brand;
    private String fuelType;
    private String engineType;
    private String plateNumber;
    private int topSpeed;
    private LocalDateTime registeredDateTime;
    private byte[] photo;

    //Derived attributes
    private Period serviceTime;

    // Access privilege controlling data
    private static final Map<String, Set<Role>> CRUD_PRIVILEGES = new HashMap<>()
    {
        {
            put("create", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to insert a vehicle
            put("read", Set.of(
                    Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER
            )); //Defines which role(s) could be able to access a vehicle
            put("update", Set.of(
                    Role.ADMIN, Role.EMPLOYEE
            )); //Defines which role(s) could be able to update a vehicle
            put("delete", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to delete a vehicle
        }
    };

    public Vehicle(String id) {
        this.id = id;
    }

    public Vehicle(String model, String brand, String fuelType, String engineType, String plateNumber, int topSpeed, LocalDateTime registeredDateTime, byte[] photo) {
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.engineType = engineType;
        this.plateNumber = plateNumber;
        this.topSpeed = topSpeed;
        this.registeredDateTime = registeredDateTime;
        this.photo = photo;
    }

    public Vehicle(String id, String model, String brand, String fuelType, String engineType, String plateNumber, int topSpeed, LocalDateTime registeredDateTime, byte[] photo) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.engineType = engineType;
        this.plateNumber = plateNumber;
        this.topSpeed = topSpeed;
        this.registeredDateTime = registeredDateTime;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public LocalDateTime getRegisteredDateTime() {
        return registeredDateTime;
    }

    public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
        this.registeredDateTime = registeredDateTime;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    public void setDerivedAttributes()
    {
        setServiceYears();
    }

    public void setServiceYears() {
        this.serviceTime = Period.between(
                LocalDate.of(
                        this.registeredDateTime.getYear(),
                        this.registeredDateTime.getMonth(),
                        this.registeredDateTime.getDayOfMonth()
                ),
                LocalDate.now()
        );
    }

    public Period getServiceTime() {
        return serviceTime;
    }

    public static Map<String, Set<Role>> getCrudPrivileges() {
        return CRUD_PRIVILEGES;
    }

    @Override
    public String toString() {

        this.setDerivedAttributes();

        return "Vehicle{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", engineType='" + engineType + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", topSpeed=" + topSpeed +
                ", registeredDateTime=" + registeredDateTime +
                ", serviceYears=" + serviceTime.getYears() +
                ", serviceMonths=" + serviceTime.getMonths() +
                ", serviceDays=" + serviceTime.getDays() +
                '}';
    }
}
