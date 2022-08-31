package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.model.Location;

import java.util.List;

public interface LocationDao
{
    Location addLocation(Location location);

    Location setLocation(Location location);

    Location deleteLocation(Location location);

    List<Location> getLocations();
}
