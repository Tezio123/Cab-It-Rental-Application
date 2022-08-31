package com.cabit.Cab_It.model;

import java.util.*;

public class Location
{
    /*
     * Basic POJO class for location
     * */
    private String id;
    private String district;
    private String zone;

    // Access privilege controlling data
    public static final Map<String, Set<Role>> CRUD_PRIVILEGES = new HashMap<>()
    {
        {
            put("create", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to insert an employee
            put("read", Set.of(
                    Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER
            )); //Defines which role(s) could be able to access an employee
            put("update", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to update an employee
            put("delete", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to delete an employee
        }
    };

    public Location(String district, String zone) {
        this.district = district;
        this.zone = zone;
    }

    public Location(String id, String district, String zone) {
        this.id = id;
        this.district = district;
        this.zone = zone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public static Map<String, Set<Role>> getCrudPrivileges() {
        return CRUD_PRIVILEGES;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", district='" + district + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
