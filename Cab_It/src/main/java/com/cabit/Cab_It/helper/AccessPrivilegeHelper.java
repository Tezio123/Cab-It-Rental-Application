package com.cabit.Cab_It.helper;

import com.cabit.Cab_It.model.*;

public class AccessPrivilegeHelper
{
    /*
     * Helper class to perform operations related to model access privileges
     * */
    public boolean checkAccessPrivilegesForAdvertisement(Object object, String operation)
    {
        if(object instanceof Admin)
        {
            Admin admin = (Admin) object;

            if(Advertisement.getCrudPrivileges().get(operation).contains(admin.getROLE()))
                return true;
        }
        else if(object instanceof Customer)
        {
            Customer customer = (Customer) object;

            if(Advertisement.getCrudPrivileges().get(operation).contains(customer.getROLE()))
                return true;
        }
        else if(object instanceof Employee)
        {
            Employee employee = (Employee) object;

            if(Advertisement.getCrudPrivileges().get(operation).contains(employee.getROLE()))
                return true;
        }
        return false;
    }
    public boolean checkAccessPrivilegesForCustomer(Object object, String operation)
    {
        if(object instanceof Admin)
        {
            Admin admin = (Admin) object;

            if(Customer.getCrudPrivileges().get(operation).contains(admin.getROLE()))
                return true;
        }
        else if(object instanceof Customer)
        {
            Customer customer = (Customer) object;

            if(Customer.getCrudPrivileges().get(operation).contains(customer.getROLE()))
                return true;
        }
        else if(object instanceof Employee)
        {
            Employee employee = (Employee) object;

            if(Customer.getCrudPrivileges().get(operation).contains(employee.getROLE()))
                return true;
        }
        return false;
    }
    public boolean checkAccessPrivilegesForEmployee(Object object, String operation)
    {
        if(object instanceof Admin)
        {
            Admin admin = (Admin) object;

            if(Employee.getCrudPrivileges().get(operation).contains(admin.getROLE()))
                return true;
        }
        else if(object instanceof Customer)
        {
            Customer customer = (Customer) object;

            if(Employee.getCrudPrivileges().get(operation).contains(customer.getROLE()))
                return true;
        }
        else if(object instanceof Employee)
        {
            Employee employee = (Employee) object;

            if(Employee.getCrudPrivileges().get(operation).contains(employee.getROLE()))
                return true;
        }
        return false;
    }
    public boolean checkAccessPrivilegesForLocation(Object object, String operation)
    {
        if(object instanceof Admin)
        {
            Admin admin = (Admin) object;

            if(Location.getCrudPrivileges().get(operation).contains(admin.getROLE()))
                return true;
        }
        else if(object instanceof Customer)
        {
            Customer customer = (Customer) object;

            if(Location.getCrudPrivileges().get(operation).contains(customer.getROLE()))
                return true;
        }
        else if(object instanceof Employee)
        {
            Employee employee = (Employee) object;

            if(Location.getCrudPrivileges().get(operation).contains(employee.getROLE()))
                return true;
        }
        return false;
    }
    public boolean checkAccessPrivilegesForOrder(Object object, String operation)
    {
        if(object instanceof Admin)
        {
            Admin admin = (Admin) object;

            if(Order.getCrudPrivileges().get(operation).contains(admin.getROLE()))
                return true;
        }
        else if(object instanceof Customer)
        {
            Customer customer = (Customer) object;

            if(Order.getCrudPrivileges().get(operation).contains(customer.getROLE()))
                return true;
        }
        else if(object instanceof Employee)
        {
            Employee employee = (Employee) object;

            if(Order.getCrudPrivileges().get(operation).contains(employee.getROLE()))
                return true;
        }
        return false;
    }
    public boolean checkAccessPrivilegesForVehicle(Object object, String operation)
    {
        if(object instanceof Admin)
        {
            Admin admin = (Admin) object;

            if(Vehicle.getCrudPrivileges().get(operation).contains(admin.getROLE()))
                return true;
        }
        else if(object instanceof Customer)
        {
            Customer customer = (Customer) object;

            if(Vehicle.getCrudPrivileges().get(operation).contains(customer.getROLE()))
                return true;
        }
        else if(object instanceof Employee)
        {
            Employee employee = (Employee) object;

            if(Vehicle.getCrudPrivileges().get(operation).contains(employee.getROLE()))
                return true;
        }
        return false;
    }
}
