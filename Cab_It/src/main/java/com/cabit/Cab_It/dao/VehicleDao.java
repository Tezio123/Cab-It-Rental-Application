package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.model.Vehicle;

import java.util.List;

public interface VehicleDao
{
    Vehicle addVehicle(Vehicle vehicle);

    Vehicle setVehicle(Vehicle vehicle);

    Vehicle setVehicleOptional(Vehicle vehicle);

    Vehicle deleteVehicle(Vehicle vehicle);

    List<Vehicle> getVehiclesAroundLocation(Location location);

    List<Vehicle> getVehicles();
}
