package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.AdminDaoMysqlImpl;
import com.cabit.Cab_It.model.*;


public class AdminService
{
    /*
     * Service class to perform services related to a admin
     * */
    private AdminDaoMysqlImpl adminDaoMysql;

    public AdminService()
    {
        this.adminDaoMysql = new AdminDaoMysqlImpl();
    }

    public Admin getAdminByUsernameAndPassword(String username, String password)
    {
        return adminDaoMysql.getAdminByUsernameAndPassword(username, password);
    }
}
