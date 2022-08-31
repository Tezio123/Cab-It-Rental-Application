package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.OrderDaoMysqlImpl;
import com.cabit.Cab_It.dao.impl.mysql.VehicleDaoMysqlImpl;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.model.Vehicle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleService
{
    /*
     * Service class to perform services related to a vehicle
     * */
    private VehicleDaoMysqlImpl vehicleDaoMysql;
    private OrderDaoMysqlImpl orderDaoMysql;

    private ImageProcessingHelper imageProcessingHelper;

    public static Map<String, Vehicle> vehicleMap;

    public VehicleService()
    {
        this.imageProcessingHelper = new ImageProcessingHelper();
        this.vehicleDaoMysql = new VehicleDaoMysqlImpl();
        this.orderDaoMysql = new OrderDaoMysqlImpl();
    }

    public void refreshVehicleMap()
    {
        vehicleMap = new HashMap<>();
        List<Vehicle> vehicles = getVehicles();

        for(Vehicle vehicle : vehicles)
            vehicleMap.put(vehicle.getId(), vehicle);
    }

    public Vehicle addVehicle(String model, String brand, String fuelType, String engineType, String plateNumber, int topSpeed,
                              File photoFile)
    {
        byte[] preprocessedPhoto = imageProcessingHelper.preprocess(photoFile);

        Vehicle vehicle = new Vehicle(
                model,
                brand,
                fuelType,
                engineType,
                plateNumber,
                topSpeed,
                null,
                preprocessedPhoto
        );

        vehicle = vehicleDaoMysql.addVehicle(vehicle);
        vehicle.setPhoto(imageProcessingHelper.decompressBytes(vehicle.getPhoto()));

        vehicleMap.put(vehicle.getId(), vehicle);

        return vehicle;
    }

    public Vehicle setVehicle(String id, String model, String brand, String fuelType, String engineType, String plateNumber, int topSpeed,
                              File photoFile)
    {
        Vehicle saved = vehicleMap.get(id);

        byte[] photo = imageProcessingHelper.compressBytes(saved.getPhoto());

        if(photoFile != null)
            photo = imageProcessingHelper.preprocess(photoFile);

        Vehicle vehicle = new Vehicle(
                id,
                model,
                brand,
                fuelType,
                engineType,
                plateNumber,
                topSpeed,
                saved.getRegisteredDateTime(),
                photo
        );

        vehicle = vehicleDaoMysql.setVehicle(vehicle);
        vehicle.setPhoto(imageProcessingHelper.decompressBytes(vehicle.getPhoto()));

        vehicleMap.put(id, vehicle);

        return vehicle;
    }

    public Vehicle setVehicleOptional(String id, String engineType, File photoFile)
    {
        Vehicle saved = vehicleMap.get(id);

        byte[] photo = imageProcessingHelper.compressBytes(saved.getPhoto());

        if(photoFile != null)
            photo = imageProcessingHelper.preprocess(photoFile);

        Vehicle vehicle = new Vehicle(
                id,
                saved.getModel(),
                saved.getBrand(),
                saved.getFuelType(),
                engineType,
                saved.getPlateNumber(),
                saved.getTopSpeed(),
                saved.getRegisteredDateTime(),
                photo
        );

        vehicle = vehicleDaoMysql.setVehicleOptional(vehicle);
        vehicle.setPhoto(imageProcessingHelper.decompressBytes(vehicle.getPhoto()));

        vehicleMap.put(id, vehicle);

        return vehicle;
    }

    public Vehicle deleteVehicle(String id)
    {
        Vehicle vehicle = VehicleService.vehicleMap.remove(id);

        Vehicle deleted = vehicleDaoMysql.deleteVehicle(vehicle);

        if(deleted == null)
            VehicleService.vehicleMap.put(vehicle.getId(), vehicle);

        return deleted;
    }

    public List<Vehicle> getVehiclesAroundLocation(String id)
    {
        Location location = LocationService.locationMap.get(id);

        return vehicleDaoMysql.getVehiclesAroundLocation(location);
    }

    public List<Vehicle> getVehicles()
    {
        return vehicleDaoMysql.getVehicles();
    }

    public Map<String, Vehicle> getVehicleMap()
    {
        return vehicleMap;
    }
}
