package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.*;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.model.*;

import java.sql.*;

public class AdminDaoMysqlImpl extends DaoSetupMysqlImpl implements AdminDao
{
    /*
    * Admin dao mysql class for handle every admin related CRUD operations
    * */
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;

    public AdminDaoMysqlImpl()
    {
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
    }

    @Override
    public Admin getAdminByUsernameAndPassword(String username, String password){
        try
        {
        	Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(Query.DML.SELECT_ADMIN_BY_UNAME_AND_PASSWORD.getQuery(),
                    username,
                    password
            ));

            return resultSetToModelConvertHelper.toAdmin(resultSet);
        }
        catch(SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
}