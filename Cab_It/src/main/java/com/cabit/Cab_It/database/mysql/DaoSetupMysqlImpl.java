package com.cabit.Cab_It.database.mysql;
import com.cabit.Cab_It.database.DaoSetup;
import com.cabit.Cab_It.helper.LoginHelper;
import com.cabit.Cab_It.service.*;

import java.sql.*;
import java.util.*;

public class DaoSetupMysqlImpl implements DaoSetup
{
    /*
    * Mysql data access initializing class
    * */
    protected static Connection connection;
    protected static LoginHelper loginHelper;

    private CustomerService customerService;
    private EmployeeService employeeService;
    private VehicleService vehicleService;
    private LocationService locationService;
    private AdvertisementService advertisementService;

    public void initDaoSetup()
    {
        loginHelper = new LoginHelper();

        this.initConnection();
        this.createTablesAndSetDefaultData();
        this.refreshMaps();
    }

    @Override
    public void initConnection(){
        try
        {
            Class.forName(DatabaseAccessCredential.DRIVER.getValue());

            try {
                connection = DriverManager.getConnection(
                        DatabaseAccessCredential.URL.getValue(),
                        DatabaseAccessCredential.USER.getValue(),
                        DatabaseAccessCredential.PASSWORD.getValue()
                );
                loginHelper.info("Successfully connected to the database");
            }
            catch (SQLException sqlException)
            {
                loginHelper.error("Database not existed, attempting to create a new one");
                createDatabase();
                loginHelper.info("Reconnecting...");
                initConnection();
            }
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            loginHelper.fatal(classNotFoundException.getMessage());
        }
    }

    @Override
    public void closeConnection() {
        try
        {
            connection.close();
            loginHelper.info("Database connection disconnected");
        }
        catch (SQLException sqlException)
        {
            loginHelper.warn("Database not closed successfully");
        }
    }

    @Override
    public void createDatabase() {

        try {
            // make temporary connection until the database will be created
            // NOTE: make sure that your database must contain a db named as 'mysql'
            Connection tempConnection = DriverManager.getConnection(
                    DatabaseAccessCredential.URL_PREFIX.getValue().concat("mysql"), // pass any database already exists in your database
                    DatabaseAccessCredential.USER.getValue(),
                    DatabaseAccessCredential.PASSWORD.getValue()
            );
            loginHelper.info("Temporarily connected to a existing database");

            Statement showDatabasesStmt = tempConnection.createStatement();
            ResultSet resultSet = showDatabasesStmt.executeQuery(Query.DML.SHOW_DATABASES.getQuery());

            while(resultSet.next())
            {
                String db = resultSet.getString("Database");

                if(db.equals(DatabaseAccessCredential.DATABASE.getValue()))
                {
                    loginHelper.info("Database already exists");
                }
            }
            Statement createDatabaseStmt = tempConnection.createStatement();
            createDatabaseStmt.execute(Query.DDL.CREATE_DATABASE.getQuery());

            loginHelper.info("Created a new database");
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
    }

    @Override
    public void createTablesAndSetDefaultData() {
        try
        {
            Statement showTableStmt = connection.createStatement();
            ResultSet resultSet = showTableStmt.executeQuery(Query.DML.SHOW_TABLES.getQuery());

            List<String> tables = Arrays.asList("admin", "advertisement", "location", "vehicle", "employee", "customer", "order", "next_id");
            List<Query.DDL> queries = new ArrayList<>(Arrays.asList(
                    Query.DDL.CREATE_TABLE_ADMIN, Query.DDL.CREATE_TABLE_ADVERTISEMENT, Query.DDL.CREATE_TABLE_LOCATION, Query.DDL.CREATE_TABLE_VEHICLE,
                    Query.DDL.CREATE_TABLE_EMPLOYEE, Query.DDL.CREATE_TABLE_CUSTOMER, Query.DDL.CREATE_TABLE_ORDER, Query.DDL.CREATE_TABLE_NEXT_ID
            ));
            List<Query.DDL> alreadyCreatedTableQueries = new ArrayList<>();

            while(resultSet.next())
            {
                Query.DDL query = queries.get
                (
                    tables.indexOf(resultSet.getString("Tables_in_".concat(DatabaseAccessCredential.DATABASE.getValue())))
                );
                alreadyCreatedTableQueries.add(query);
            }
            if(alreadyCreatedTableQueries.size() != queries.size()) {

                queries.removeAll(alreadyCreatedTableQueries);

                String tableStatus = alreadyCreatedTableQueries.size() == 0 ? "No any table exists in the database" :
                        queries.size() + " Table(s) are missed";
                loginHelper.info(tableStatus);

                Statement createTableStmt = connection.createStatement();

                for (Query.DDL query : queries) {
                    createTableStmt.execute(query.getQuery());
                }
                loginHelper.info("Successfully created all the tables");

                Statement initDefaultDataStmt = connection.createStatement();

                if(!alreadyCreatedTableQueries.contains(Query.DDL.CREATE_TABLE_NEXT_ID)) // init table 'next_id' with data if it is not already created
                    initDefaultDataStmt.execute(Query.DML.INIT_NEXT_ID.getQuery());
                if(!alreadyCreatedTableQueries.contains(Query.DDL.CREATE_TABLE_ADMIN)) // init table 'admin' with data if it is not already created
                    initDefaultDataStmt.execute(Query.DML.INIT_DEFAULT_ADMIN.getQuery());
            }
            else
                loginHelper.info("Tables already created");

            loginHelper.info("Successfully initialized default data");
        }
        catch (SQLException sqlException)
        {
            int errCode = sqlException.getErrorCode();

            if(errCode == 1062) // neglected error (Duplicate entry for primary key)
            {
                loginHelper.warn(sqlException.getMessage());
                loginHelper.info("Successfully initialized default data");
                return;
            }
            loginHelper.fatal(sqlException.getMessage());
        }
    }

    @Override
    public void refreshMaps() {
        customerService = new CustomerService();
        employeeService = new EmployeeService();
        vehicleService = new VehicleService();
        locationService = new LocationService();
        advertisementService = new AdvertisementService();

        locationService.refreshLocationMap();
        vehicleService.refreshVehicleMap();
        advertisementService.refreshAdvertisementMap();
        customerService.refreshCustomerMap();
        employeeService.refreshEmployeeMap();

        loginHelper.info("Successfully initialized data maps");
        loginHelper.info("Ready to go :)");
    }

    public int getNextId() {
        try
        {
            Statement statement = connection.createStatement();;
            ResultSet resultSet = statement.executeQuery(Query.DML.GET_NEXT_ID.getQuery());

            if(resultSet.next())
            {
                int id = resultSet.getInt("next_val");

                setNextID(id + 1);
                return id;
            }
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return -1;
    }

    private void setNextID(int id)
    {
        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate(String.format(Query.DML.UPDATE_NEXT_ID.getQuery(),
                id
            ));
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
    }
}