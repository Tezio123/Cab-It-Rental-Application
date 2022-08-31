package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.CustomerDao;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.helper.SequenceIdGenerateHelper;
import com.cabit.Cab_It.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoMysqlImpl extends DaoSetupMysqlImpl implements CustomerDao
{
    /*
     * Customer dao mysql class for handle every customer related CRUD operations
     * */
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;
    private SequenceIdGenerateHelper sequenceIdGenerateHelper;
    private ImageProcessingHelper imageProcessingHelper;

    public CustomerDaoMysqlImpl()
    {
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
        this.sequenceIdGenerateHelper = new SequenceIdGenerateHelper();
        this.imageProcessingHelper = new ImageProcessingHelper();
    }

    @Override
    public Customer getCustomerByUsernameAndPassword(String username, String password) {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(Query.DML.SELECT_CUSTOMER_BY_UNAME_AND_PASSWORD.getQuery(),
                    username,
                    password
            ));

            return resultSetToModelConvertHelper.toCustomer(resultSet);
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        try
        {
            int nextId = getNextId();
            String id = sequenceIdGenerateHelper.generateCustomerId(nextId);

            customer.setId(id);

            File photoFile = imageProcessingHelper.toFile(customer.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.INSERT_CUSTOMER.getQuery());

            statement.setString(1, customer.getId());
            statement.setString(2, customer.getNic());
            statement.setString(3, customer.getUsername());
            statement.setString(4, customer.getPassword());
            statement.setString(5, customer.getFirstName());
            statement.setString(6, customer.getMidName());
            statement.setString(7, customer.getLastName());
            statement.setString(8, customer.getAddressLine1());
            statement.setString(9, customer.getAddressLine2());
            statement.setString(10, customer.getAddressLine3());
            statement.setString(11, customer.getCity());
            statement.setString(12, customer.getPhone());
            statement.setBlob(13, photoInputStream);

            statement.executeUpdate();

            return customer;
        }
        catch (SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Customer setCustomer(Customer customer) {
        try
        {
            File photoFile = imageProcessingHelper.toFile(customer.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.UPDATE_CUSTOMER.getQuery());

            statement.setString(1, customer.getNic());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getFirstName());
            statement.setString(5, customer.getMidName());
            statement.setString(6, customer.getLastName());
            statement.setString(7, customer.getAddressLine1());
            statement.setString(8, customer.getAddressLine2());
            statement.setString(9, customer.getAddressLine3());
            statement.setString(10, customer.getCity());
            statement.setString(11, customer.getPhone());
            statement.setBlob(12, photoInputStream);
            statement.setString(13, customer.getId());

            statement.executeUpdate();

            return customer;
        }
        catch(SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(String.format(Query.DML.DELETE_CUSTOMER.getQuery(),
                customer.getId()
            ));

            return customer;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getCustomers() {

        List<Customer> customers = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query.DML.SELECT_CUSTOMERS.getQuery());

            Customer customer = resultSetToModelConvertHelper.toCustomer(resultSet);

            while (customer != null)
            {
                customers.add(customer);
                customer = resultSetToModelConvertHelper.toCustomer(resultSet);
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return customers;
    }
}
