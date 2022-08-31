package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.model.Admin;

public interface AdminDao
{
    Admin getAdminByUsernameAndPassword(String username, String password);
}
