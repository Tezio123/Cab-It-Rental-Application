package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.EmployeeDao;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.helper.SequenceIdGenerateHelper;
import com.cabit.Cab_It.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class EmployeeDaoMysqlImpl extends DaoSetupMysqlImpl implements EmployeeDao
{
    /*
     * Employee dao mysql class for handle every employee related CRUD operations
     * */
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;
    private DateTimeHelper dateTimeHelper;
    private ImageProcessingHelper imageProcessingHelper;
    private SequenceIdGenerateHelper sequenceIdGenerateHelper;

    public EmployeeDaoMysqlImpl()
    {
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
        this.dateTimeHelper = new DateTimeHelper();
        this.imageProcessingHelper = new ImageProcessingHelper();
        this.sequenceIdGenerateHelper = new SequenceIdGenerateHelper();
    }
    @Override
    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(Query.DML.SELECT_EMPLOYEE_BY_UNAME_AND_PASSWORD.getQuery(),
                    username,
                    password
            ));
            return resultSetToModelConvertHelper.toEmployee(resultSet);
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
            return null;
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        try
        {
            int nextId = getNextId();
            String id = sequenceIdGenerateHelper.generateEmployeeId(nextId);
            LocalDateTime dateTime = dateTimeHelper.getDateTime();

            employee.setId(id);
            employee.setRegisteredDateTime(dateTime);

            File photoFile = imageProcessingHelper.toFile(employee.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.INSERT_EMPLOYEE.getQuery());

            statement.setString(1, employee.getId());
            statement.setString(2, employee.getUsername());
            statement.setString(3, employee.getPassword());
            statement.setString(4, employee.getNic());
            statement.setString(5, employee.getFirstName());
            statement.setString(6, employee.getMidName());
            statement.setString(7, employee.getLastName());
            statement.setString(8, employee.getAddressLine1());
            statement.setString(9, employee.getAddressLine2());
            statement.setString(10, employee.getAddressLine3());
            statement.setString(11, employee.getCity());
            statement.setString(12, employee.getPhone());
            statement.setString(13, employee.getBloodGroup());
            statement.setString(14, employee.getLicense());
            statement.setTimestamp(15, Timestamp.valueOf(employee.getRegisteredDateTime()));
            statement.setBlob(16, photoInputStream);
            statement.setString(17, employee.getLocation().getId());
            statement.setString(18, employee.getVehicle().getId());

            statement.executeUpdate();

            return employee;
        }
        catch (SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Employee setEmployee(Employee employee) {

        try{
            File photoFile = imageProcessingHelper.toFile(employee.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.UPDATE_EMPLOYEE.getQuery());

            statement.setString(1, employee.getUsername());
            statement.setString(2, employee.getPassword());
            statement.setString(3, employee.getNic());
            statement.setString(4, employee.getFirstName());
            statement.setString(5, employee.getMidName());
            statement.setString(6, employee.getLastName());
            statement.setString(7, employee.getAddressLine1());
            statement.setString(8, employee.getAddressLine2());
            statement.setString(9, employee.getAddressLine3());
            statement.setString(10, employee.getCity());
            statement.setString(11, employee.getPhone());
            statement.setString(12, employee.getBloodGroup());
            statement.setString(13, employee.getLicense());
            statement.setBlob(14, photoInputStream);
            statement.setString(15, employee.getLocation().getId());
            statement.setString(16, employee.getVehicle().getId());
            statement.setString(17, employee.getId());

            statement.executeUpdate();

            return employee;
        }
        catch (SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Employee setEmployeeOptional(Employee employee) {
        try{

            File photoFile = imageProcessingHelper.toFile(employee.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.UPDATE_EMPLOYEE_OPTIONAL.getQuery());

            statement.setString(1, employee.getUsername());
            statement.setString(2, employee.getPassword());
            statement.setString(3, employee.getAddressLine1());
            statement.setString(4, employee.getAddressLine2());
            statement.setString(5, employee.getAddressLine3());
            statement.setString(6, employee.getCity());
            statement.setString(7, employee.getPhone());
            statement.setBlob(8, photoInputStream);
            statement.setString(9, employee.getId());

            statement.executeUpdate();

            return employee;
        }
        catch (SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Employee deleteEmployee(Employee employee) {

        String id = employee.getId();
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(
                    Query.DML.DELETE_EMPLOYEE.getQuery(),
                    id
            ));

            return employee;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query.DML.SELECT_EMPLOYEES.getQuery());

            return resultSetToModelConvertHelper.toEmployees(resultSet);
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
}
