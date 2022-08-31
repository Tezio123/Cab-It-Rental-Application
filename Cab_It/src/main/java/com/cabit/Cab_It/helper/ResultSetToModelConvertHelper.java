package com.cabit.Cab_It.helper;

import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.model.*;
import com.cabit.Cab_It.service.CustomerService;
import com.cabit.Cab_It.service.LocationService;
import com.cabit.Cab_It.service.VehicleService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ResultSetToModelConvertHelper extends DaoSetupMysqlImpl
{
    /*
     * Helper class to perform conversions of result sets to model objects
     * */
    private ImageProcessingHelper imageProcessingHelper;
    private DateTimeHelper dateTimeHelper;

    public ResultSetToModelConvertHelper()
    {
        this.imageProcessingHelper = new ImageProcessingHelper();
        this.dateTimeHelper = new DateTimeHelper();
    }

    public Admin toAdmin(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next())
            {
                return new Admin(
                        resultSet.getString("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
    public Employee toEmployee(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next()) {
                return new Employee(
                        resultSet.getString("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("nic"),
                        resultSet.getString("first_name"),
                        resultSet.getString("mid_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address_line_1"),
                        resultSet.getString("address_line_2"),
                        resultSet.getString("address_line_3"),
                        resultSet.getString("city"),
                        resultSet.getString("phone"),
                        resultSet.getString("blood_group"),
                        resultSet.getString("license"),
                        dateTimeHelper.getFormattedDateTime(resultSet.getString("registered_date_time"),
                                "yyyy-MM-dd HH:mm:ss.S"),
                        imageProcessingHelper.postprocess(resultSet.getBlob("photo")),
                        LocationService.locationMap.get(resultSet.getString("location_id")),
                        VehicleService.vehicleMap.get(resultSet.getString("vehicle_id"))
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    public List<Employee> toEmployees(ResultSet resultSet)
    {
            List<Employee> employees = new ArrayList<>();
            Employee employee = toEmployee(resultSet);

            while (employee != null)
            {
                employees.add(employee);
                employee = toEmployee(resultSet);
            }
            return employees;
    }

    public Vehicle toVehicle(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next()) {
                return new Vehicle(
                        resultSet.getString("id"),
                        resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getString("fuel_type"),
                        resultSet.getString("engine_type"),
                        resultSet.getString("plate_number"),
                        resultSet.getInt("top_speed"),
                        dateTimeHelper.getFormattedDateTime(resultSet.getString("registered_date_time"),
                                "yyyy-MM-dd HH:mm:ss.S"),
                        imageProcessingHelper.postprocess(resultSet.getBlob("photo"))
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    public List<Vehicle> toVehicles(ResultSet resultSet)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = toVehicle(resultSet);

        while (vehicle != null)
        {
            vehicles.add(vehicle);
            vehicle = toVehicle(resultSet);
        }
        return vehicles;
    }

    public Advertisement toAdvertisement(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next()) {
                return new Advertisement(
                        resultSet.getString("id"),
                        dateTimeHelper.getFormattedDateTime(resultSet.getString("introduced_date_time"),
                                "yyyy-MM-dd HH:mm:ss.S"),
                        resultSet.getString("content"),
                        imageProcessingHelper.postprocess(resultSet.getBlob("photo"))
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    public List<Advertisement> toAdvertisements(ResultSet resultSet)
    {
        List<Advertisement> advertisements = new ArrayList<>();
        Advertisement advertisement = toAdvertisement(resultSet);

        while (advertisement != null)
        {
            advertisements.add(advertisement);
            advertisement = toAdvertisement(resultSet);
        }

        return advertisements;
    }

    public Order toOrder(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next()) {

                String customerId = resultSet.getString("customer_id");
                String vehicleId = resultSet.getString("vehicle_id");
                String fromLocationId = resultSet.getString("from_location_id");
                String toLocationId = resultSet.getString("to_location_id");
                String response = resultSet.getString("response");
                String review = resultSet.getString("review");
                LocalDateTime requestedDateTime = dateTimeHelper.getFormattedDateTime(resultSet.getString("requested_date_time"),
                        "yyyy-MM-dd HH:mm:ss.S");

                LocalDateTime respondedDateTime = null;

                if(response != null && response.equals("ACCEPTED"))
                {
                    respondedDateTime = dateTimeHelper.getFormattedDateTime(resultSet.getString("responded_date_time"),
                            "yyyy-MM-dd HH:mm:ss.S");
                }
                return new Order(
                        CustomerService.customerMap.get(customerId),
                        VehicleService.vehicleMap.get(vehicleId),
                        LocationService.locationMap.get(fromLocationId),
                        LocationService.locationMap.get(toLocationId),
                        response,
                        review,
                        requestedDateTime,
                        respondedDateTime
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    public List<Order> toOrders(ResultSet resultSet)
    {
        List<Order> orders = new ArrayList<>();
        Order order = toOrder(resultSet);

        while (order != null)
        {
            orders.add(order);
            order = toOrder(resultSet);
        }
        return orders;
    }
    public Customer toCustomer(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next())
            {
                return new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("nic"),
                        resultSet.getString("first_name"),
                        resultSet.getString("mid_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address_line_1"),
                        resultSet.getString("address_line_2"),
                        resultSet.getString("address_line_3"),
                        resultSet.getString("city"),
                        resultSet.getString("phone"),
                        imageProcessingHelper.postprocess(resultSet.getBlob("photo"))
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    public Location toLocation(ResultSet resultSet)
    {
        try
        {
            if(resultSet.next())
            {
                return new Location(
                        resultSet.getString("id"),
                        resultSet.getString("district"),
                        resultSet.getString("zone")
                );
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
}
