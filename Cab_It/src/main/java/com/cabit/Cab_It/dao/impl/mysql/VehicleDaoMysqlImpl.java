package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.VehicleDao;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.helper.SequenceIdGenerateHelper;
import com.cabit.Cab_It.model.Location;
import com.cabit.Cab_It.model.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoMysqlImpl extends DaoSetupMysqlImpl implements VehicleDao
{
    /*
     * Vehicle dao mysql class for handle every vehicle related CRUD operations
     * */
    private SequenceIdGenerateHelper sequenceIdGenerateHelper;
    private DateTimeHelper dateTimeHelper;
    private ImageProcessingHelper imageProcessingHelper;
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;

    public VehicleDaoMysqlImpl()
    {
        this.sequenceIdGenerateHelper = new SequenceIdGenerateHelper();
        this.dateTimeHelper = new DateTimeHelper();
        this.imageProcessingHelper = new ImageProcessingHelper();
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        try
        {
            int nextId = getNextId();
            String id = sequenceIdGenerateHelper.generateVehicleId(nextId);
            LocalDateTime dateTime = dateTimeHelper.getDateTime();

            vehicle.setId(id);
            vehicle.setRegisteredDateTime(dateTime);

            File photoFile = imageProcessingHelper.toFile(vehicle.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.INSERT_VEHICLE.getQuery());

            statement.setString(1, vehicle.getId());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getBrand());
            statement.setString(4, vehicle.getFuelType());
            statement.setString(5, vehicle.getEngineType());
            statement.setString(6, vehicle.getPlateNumber());
            statement.setInt(7, vehicle.getTopSpeed());
            statement.setTimestamp(8, Timestamp.valueOf(vehicle.getRegisteredDateTime()));
            statement.setBlob(9, photoInputStream);

            statement.executeUpdate();

            return vehicle;
        }
        catch (SQLException | IOException exception) {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Vehicle setVehicle(Vehicle vehicle) {
        try
        {
            File photoFile = imageProcessingHelper.toFile(vehicle.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.UPDATE_VEHICLE.getQuery());

            statement.setString(1, vehicle.getModel());
            statement.setString(2, vehicle.getBrand());
            statement.setString(3, vehicle.getFuelType());
            statement.setString(4, vehicle.getEngineType());
            statement.setString(5, vehicle.getPlateNumber());
            statement.setInt(6, vehicle.getTopSpeed());
            statement.setBlob(7, photoInputStream);
            statement.setString(8, vehicle.getId());

            statement.executeUpdate();

            return vehicle;
        }
        catch(SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Vehicle deleteVehicle(Vehicle vehicle) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.DELETE_VEHICLE.getQuery(),
                    vehicle.getId()
            ));

            return vehicle;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query.DML.SELECT_VEHICLES.getQuery());

            Vehicle vehicle = resultSetToModelConvertHelper.toVehicle(resultSet);

            while (vehicle != null)
            {
                vehicles.add(vehicle);
                vehicle = resultSetToModelConvertHelper.toVehicle(resultSet);
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return vehicles;
    }

    @Override
    public Vehicle setVehicleOptional(Vehicle vehicle) {
        try
        {
            File photoFile = imageProcessingHelper.toFile(vehicle.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.UPDATE_VEHICLE_OPTIONAL.getQuery());

            statement.setString(1, vehicle.getEngineType());
            statement.setBlob(2, photoInputStream);
            statement.setString(3, vehicle.getId());

            statement.executeUpdate();

            return vehicle;
        }
        catch(SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Vehicle> getVehiclesAroundLocation(Location location) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(Query.DML.SELECT_AVAILABLE_VEHICLES_NEAR_LOCATION.getQuery(),
                    location.getId()
            ));

            return resultSetToModelConvertHelper.toVehicles(resultSet);
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
}
