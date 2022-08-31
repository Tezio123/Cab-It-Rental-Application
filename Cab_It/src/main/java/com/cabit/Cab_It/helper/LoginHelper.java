package com.cabit.Cab_It.helper;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LoginHelper
{
    private static Logger logger = Logger.getLogger(LoginHelper.class.getName());

    public LoginHelper()
    {
    	BasicConfigurator.configure();
	}

    public void fatal(String message)
    {
        logger.fatal(message);
    }

    public void error(String message)
    {
        logger.error(message);
    }
    public void warn(String message)
    {
        logger.warn(message);
    }

    public void info(String message)
    {
        logger.info(message);
    }
}
