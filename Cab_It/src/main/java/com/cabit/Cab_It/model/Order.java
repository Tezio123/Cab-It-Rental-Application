package com.cabit.Cab_It.model;

import java.time.LocalDateTime;
import java.util.*;

public class Order
{
    /*
     * Basic POJO class for order
     * */
    private Customer customer;
    private Vehicle vehicle;
    private Location fromLocation;
    private Location toLocation;
    private String response;
    private String review;
    private LocalDateTime requestedDateTime;
    private LocalDateTime respondedDateTime;

    // Access privilege controlling data
    private static final Map<String, Set<Role>> CRUD_PRIVILEGES = new HashMap<>()
    {
        {
            put("create", Set.of(
                    Role.CUSTOMER
            )); //Defines which role(s) could be able to insert an order
            put("read", Set.of(
                    Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER
            )); //Defines which role(s) could be able to access an order
            put("update", Set.of(
                    Role.CUSTOMER
            )); //Defines which role(s) could be able to update an order
            put("delete", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to delete an order
        }
    };

    public Order(Customer customer,
                 Vehicle vehicle,
                 Location fromLocation,
                 Location toLocation,
                 String response,
                 String review,
                 LocalDateTime requestedDateTime,
                 LocalDateTime respondedDateTime)
    {
        this.customer = customer;
        this.vehicle = vehicle;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.response = response;
        this.review = review;
        this.requestedDateTime = requestedDateTime;
        this.respondedDateTime = respondedDateTime;
    }
    public Order(Customer customer,
                 Vehicle vehicle,
                 Location fromLocation,
                 Location toLocation)
    {
        this.customer = customer;
        this.vehicle = vehicle;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public Order(Customer customer,
                 Vehicle vehicle,
                 Location fromLocation,
                 Location toLocation,
                 LocalDateTime requestedDateTime)
    {
        this.customer = customer;
        this.vehicle = vehicle;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.requestedDateTime = requestedDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(LocalDateTime requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public LocalDateTime getRespondedDateTime() {
        return respondedDateTime;
    }

    public void setRespondedDateTime(LocalDateTime respondedDateTime) {
        this.respondedDateTime = respondedDateTime;
    }

    public static Map<String, Set<Role>> getCrudPrivileges() {
        return CRUD_PRIVILEGES;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", vehicle=" + vehicle +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                ", response='" + response + '\'' +
                ", review='" + review + '\'' +
                ", requestedDateTime=" + requestedDateTime +
                ", respondedDateTime=" + respondedDateTime +
                '}';
    }
}