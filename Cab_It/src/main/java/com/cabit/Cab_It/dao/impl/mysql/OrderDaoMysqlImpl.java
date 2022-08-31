package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.OrderDao;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.model.Customer;
import com.cabit.Cab_It.model.Order;
import com.cabit.Cab_It.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDaoMysqlImpl extends DaoSetupMysqlImpl implements OrderDao
{
    /*
     * Order dao mysql class for handle every order related CRUD operations
     * */
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;
    private DateTimeHelper dateTimeHelper;

    public OrderDaoMysqlImpl()
    {
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
        this.dateTimeHelper = new DateTimeHelper();
    }

    @Override
    public List<Order> getOrders(boolean dateTimeInDsc) {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(Query.DML.SELECT_ORDERS.getQuery(),
                    dateTimeInDsc ? "DESC" : "ASC"
            ));

            return resultSetToModelConvertHelper.toOrders(resultSet);
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public <U> List<Order> getOrders(U entity, boolean dateTimeInDsc) {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            String ascDescState = dateTimeInDsc ? "DESC" : "ASC";

            if (entity instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) entity;

                    resultSet = statement.executeQuery(String.format(Query.DML.SELECT_ORDERS_OF_VEHICLE.getQuery(),
                            vehicle.getId(),
                            ascDescState
                    ));

            }
            else if(entity instanceof Customer)
            {
                Customer customer = (Customer) entity;

                resultSet = statement.executeQuery(String.format(Query.DML.SELECT_ORDERS_OF_CUSTOMER.getQuery(),
                        customer.getId(),
                        ascDescState
                ));
            }
            return resultSetToModelConvertHelper.toOrders(resultSet);
        }
        catch (SQLException sqlException) {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }


    @Override
    public Order acceptOrder(Order order) {
        try
        {
            LocalDateTime dateTime = dateTimeHelper.getDateTime();

            order.setResponse("ACCEPT");
            order.setRespondedDateTime(dateTime);

            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.ACCEPT_ORDER.getQuery(),
                    order.getRespondedDateTime(),
                    order.getCustomer().getId(),
                    order.getVehicle().getId(),
                    order.getFromLocation().getId(),
                    order.getToLocation().getId(),
                    order.getRequestedDateTime()
            ));
            return order;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        try {
            order.setRequestedDateTime(dateTimeHelper.getDateTime());

            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.CREATE_ORDER.getQuery(),
                    order.getCustomer().getId(),
                    order.getVehicle().getId(),
                    order.getFromLocation().getId(),
                    order.getToLocation().getId(),
                    order.getRequestedDateTime()
            ));
            return order;

        } catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Order setOrder(Order preOrder, Order newOrder) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.UPDATE_ORDER.getQuery(),
                    newOrder.getVehicle().getId(),
                    newOrder.getFromLocation().getId(),
                    newOrder.getToLocation().getId(),
                    preOrder.getCustomer().getId(),
                    preOrder.getVehicle().getId(),
                    preOrder.getFromLocation().getId(),
                    preOrder.getToLocation().getId()
            ));

            return newOrder;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Order deleteOrder(Order order) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.DELETE_ORDER.getQuery(),
                    order.getCustomer().getId(),
                    order.getVehicle().getId(),
                    order.getFromLocation().getId(),
                    order.getToLocation().getId(),
                    order.getRequestedDateTime()
            ));

            return order;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Order reviewOrder(Order order, String review) {
        try
        {
            order.setReview(review);

            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.REVIEW_ORDER.getQuery(),
                    order.getReview(),
                    order.getCustomer().getId(),
                    order.getVehicle().getId(),
                    order.getFromLocation().getId(),
                    order.getToLocation().getId(),
                    order.getRequestedDateTime()
            ));

            return order;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
}
