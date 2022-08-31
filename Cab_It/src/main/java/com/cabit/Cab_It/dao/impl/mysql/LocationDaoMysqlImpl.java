package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.LocationDao;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.helper.SequenceIdGenerateHelper;
import com.cabit.Cab_It.model.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoMysqlImpl extends DaoSetupMysqlImpl implements LocationDao
{
    /*
     * Location dao mysql class for handle every location related CRUD operations
     * */
    private SequenceIdGenerateHelper sequenceIdGenerateHelper;
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;

    public LocationDaoMysqlImpl()
    {
        this.sequenceIdGenerateHelper = new SequenceIdGenerateHelper();
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
    }

    @Override
    public Location addLocation(Location location) {
        try
        {
            int nextId = getNextId();
            String id = sequenceIdGenerateHelper.generateLocationId(nextId);

            location.setId(id);

            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.INSERT_LOCATION.getQuery(),
                    location.getId(),
                    location.getDistrict(),
                    location.getZone()
            ));

            return location;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Location setLocation(Location location) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.UPDATE_LOCATION.getQuery(),
                    location.getDistrict(),
                    location.getZone(),
                    location.getId()
            ));

            return location;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Location deleteLocation(Location location) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.DELETE_LOCATION.getQuery(),
                    location.getId()
            ));

            return location;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Location> getLocations() {

        List<Location> locations = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query.DML.SELECT_LOCATIONS.getQuery());

            Location location = resultSetToModelConvertHelper.toLocation(resultSet);

            while (location != null)
            {
                locations.add(location);
                location = resultSetToModelConvertHelper.toLocation(resultSet);
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return locations;
    }
}
