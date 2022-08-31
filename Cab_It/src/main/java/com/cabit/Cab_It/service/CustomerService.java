package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.CustomerDaoMysqlImpl;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.model.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService
{
    /*
     * Service class to perform services related to a customer
     * */
    private CustomerDaoMysqlImpl customerDaoMysql;

    private ImageProcessingHelper imageProcessingHelper;

    public static Map<String, Customer> customerMap;

    public CustomerService()
    {
        this.customerDaoMysql = new CustomerDaoMysqlImpl();
        this.imageProcessingHelper = new ImageProcessingHelper();
    }

    public void refreshCustomerMap()
    {
        customerMap = new HashMap<>();
        List<Customer> customers = customerDaoMysql.getCustomers();

        for(Customer customer : customers)
            customerMap.put(customer.getId(), customer);
    }

    public Customer getCustomerByUsernameAndPassword(String username, String password)
    {
        return customerDaoMysql.getCustomerByUsernameAndPassword(username, password);
    }

    public Customer addCustomer(String username,
                                String password,
                                String nic,
                                String firstName,
                                String midName,
                                String lastName,
                                String addressLine1,
                                String addressLine2,
                                String addressLine3,
                                String city,
                                String phone,
                                File photoFile)
    {
        byte[] compressedPhoto = imageProcessingHelper.preprocess(photoFile);

        Customer customer = new Customer(
                username,
                password,
                nic,
                firstName,
                midName,
                lastName,
                addressLine1,
                addressLine2,
                addressLine3,
                city,
                phone,
                compressedPhoto
        );

        customer = customerDaoMysql.addCustomer(customer);
        customer.setPhoto(imageProcessingHelper.decompressBytes(customer.getPhoto()));

        customerMap.put(customer.getId(), customer);

        return customer;
    }

    public Customer setCustomer(String id, String username, String password, String nic, String firstName, String midName, String lastName, String addressLine1, String addressLine2, String addressLine3, String city, String phone, File photoFile)
    {
        Customer saved = customerMap.get(id);

        byte[] photo = imageProcessingHelper.compressBytes(saved.getPhoto());

        if(photoFile != null)
            photo = imageProcessingHelper.preprocess(photoFile);

        Customer customer = new Customer(
                id,
                username,
                password,
                nic,
                firstName,
                midName,
                lastName,
                addressLine1,
                addressLine2,
                addressLine3,
                city,
                phone,
                photo
        );

        customer = customerDaoMysql.setCustomer(customer);
        customer.setPhoto(imageProcessingHelper.decompressBytes(customer.getPhoto()));

        customerMap.put(id, customer);

        return customer;
    }

    public Customer deleteCustomer(String id)
    {
        Customer customer = CustomerService.customerMap.remove(id);
        Customer deleted = customerDaoMysql.deleteCustomer(customer);

        if(deleted == null)
            CustomerService.customerMap.put(customer.getId(), customer);

        return deleted;
    }

    public List<Customer> getCustomers()
    {
        return customerDaoMysql.getCustomers();
    }
}
