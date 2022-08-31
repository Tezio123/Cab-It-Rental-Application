package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.database.DaoSetup;
import com.cabit.Cab_It.model.Employee;

import java.util.List;

public interface EmployeeDao extends DaoSetup
{
    Employee getEmployeeByUsernameAndPassword(String username, String password);

    Employee addEmployee(Employee employee);

    Employee setEmployee(Employee employee);

    Employee setEmployeeOptional(Employee employee);

    Employee deleteEmployee(Employee employee);

    List<Employee> getEmployees();
}
