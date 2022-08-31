package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.LocationDaoMysqlImpl;
import com.cabit.Cab_It.model.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationService
{
    /*
     * Service class to perform services related to a location
     * */
    private LocationDaoMysqlImpl locationDaoMysqlImpl;

    public static Map<String, Location> locationMap;

    public LocationService()
    {
        this.locationDaoMysqlImpl = new LocationDaoMysqlImpl();
    }

    public void refreshLocationMap()
    {
        List<Location> locations = locationDaoMysqlImpl.getLocations();
        locationMap = new HashMap<>();

        for(Location location : locations)
            locationMap.put(location.getId(), location);
    }

    public Location addLocation(String district, String zone)
    {
        Location location = new Location(district, zone);

        location = locationDaoMysqlImpl.addLocation(location);
        locationMap.put(location.getId(), location);

        return location;
    }

    public Location setLocation(String id, String district, String zone) {
        Location location = new Location(id, district, zone);

        location = locationDaoMysqlImpl.setLocation(location);
        locationMap.put(id, location);

        return location;
    }

    public Location deleteLocation(String id)
    {
        Location location = LocationService.locationMap.remove(id);
        Location deleted = locationDaoMysqlImpl.deleteLocation(location);

        if(deleted == null)
            LocationService.locationMap.put(location.getId(), location);

        return deleted;
    }

    public List<Location> getLocations()
    {
        return locationDaoMysqlImpl.getLocations();
    }

    public Map<String, Location> getLocationMap()
    {
        return locationMap;
    }
}
