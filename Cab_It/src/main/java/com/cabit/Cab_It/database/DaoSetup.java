package com.cabit.Cab_It.database;

public interface DaoSetup
{
    /*
    * An interface to interact with all the data initializing implementations
    * */
    void initConnection();
    void closeConnection();
    void createDatabase();
    void createTablesAndSetDefaultData();
    void refreshMaps();
}
