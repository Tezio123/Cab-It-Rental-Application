package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.model.Customer;

import java.util.List;

public interface CustomerDao
{
    Customer getCustomerByUsernameAndPassword(String username, String password);

    Customer addCustomer(Customer customer);

    Customer setCustomer(Customer customer);

    Customer deleteCustomer(Customer customer);

    List<Customer> getCustomers();
}
