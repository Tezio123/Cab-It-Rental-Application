package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerMysql implements ServletContextListener
{
    /*
     * Listener class to instantiate database tables and data with start of the tomcat
     * and close the connection at the time of tomcat stopped.
     * */
    private DaoSetupMysqlImpl daoSetupMysql;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	daoSetupMysql = new DaoSetupMysqlImpl();
        daoSetupMysql.initDaoSetup(); // initialize database setup
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        daoSetupMysql.closeConnection(); // disconnect database connection
    }
}
