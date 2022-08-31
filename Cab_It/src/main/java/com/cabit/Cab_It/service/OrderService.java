package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.OrderDaoMysqlImpl;
import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.model.Order;
import com.cabit.Cab_It.model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    /*
     * Service class to perform services related to a order
     * */
    private OrderDaoMysqlImpl orderDaoMysqlImpl;

    public OrderService() {
        this.orderDaoMysqlImpl = new OrderDaoMysqlImpl();
    }

    public Order addOrder(String customerId, String vehicleId, String fromLocationId, String toLocationId) {
        Order order = new Order(
                CustomerService.customerMap.get(customerId),
                VehicleService.vehicleMap.get(vehicleId),
                LocationService.locationMap.get(fromLocationId),
                LocationService.locationMap.get(toLocationId)
        );

        return orderDaoMysqlImpl.addOrder(order);
    }

    public Order setOrder(String prevCustomerId,
                          String prevVehicleId,
                          String prevFromLocationId,
                          String prevToLocationId,
                          String newCustomerId,
                          String newVehicleId,
                          String newFromLocationId,
                          String newToLocationId)
    {
        Order prevOrder = new Order(
                CustomerService.customerMap.get(prevCustomerId),
                VehicleService.vehicleMap.get(prevVehicleId),
                LocationService.locationMap.get(prevFromLocationId),
                LocationService.locationMap.get(prevToLocationId)
        );

        Order newOrder = new Order(
                CustomerService.customerMap.get(newCustomerId),
                VehicleService.vehicleMap.get(newVehicleId),
                LocationService.locationMap.get(newFromLocationId),
                LocationService.locationMap.get(newToLocationId)
        );

        return orderDaoMysqlImpl.setOrder(prevOrder, newOrder);
    }

    public Order deleteOrder(String customerId,
                             String vehicleId,
                             String fromLocationId,
                             String toLocationId,
                             LocalDateTime requestedDateTime)
    {
        Order order = new Order(
                CustomerService.customerMap.get(customerId),
                VehicleService.vehicleMap.get(vehicleId),
                LocationService.locationMap.get(fromLocationId),
                LocationService.locationMap.get(toLocationId),
                requestedDateTime
        );

        return orderDaoMysqlImpl.deleteOrder(order);
    }
    public <U> List<Order> getOrders(U entity, boolean ascDescState)
    {
        if(entity instanceof Vehicle || entity instanceof  Customer)
            return orderDaoMysqlImpl.getOrders(entity, ascDescState);

        return orderDaoMysqlImpl.getOrders(ascDescState);
    }

    public Order reviewOrder(String customerId,
                             String vehicleId,
                             String fromLocationId,
                             String toLocationId,
                             LocalDateTime requestedDateTime,
                             String review)
    {
        Order order = new Order(
                CustomerService.customerMap.get(customerId),
                VehicleService.vehicleMap.get(vehicleId),
                LocationService.locationMap.get(fromLocationId),
                LocationService.locationMap.get(toLocationId),
                requestedDateTime
        );

        return orderDaoMysqlImpl.reviewOrder(order, review);
    }
    public Order acceptOrder(String customerId,
                             String vehicleId,
                             String fromLocationId,
                             String toLocationId,
                             LocalDateTime requestedDateTime)
    {
        Order order = new Order(
                CustomerService.customerMap.get(customerId),
                VehicleService.vehicleMap.get(vehicleId),
                LocationService.locationMap.get(fromLocationId),
                LocationService.locationMap.get(toLocationId),
                requestedDateTime
        );

        return orderDaoMysqlImpl.acceptOrder(order);
    }
}
