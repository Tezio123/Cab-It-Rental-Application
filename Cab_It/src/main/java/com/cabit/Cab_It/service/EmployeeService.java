package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.EmployeeDaoMysqlImpl;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.model.Employee;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService
{
    /*
     * Service class to perform services related to a employee
     * */
    private EmployeeDaoMysqlImpl employeeDaoMysql;
    private ImageProcessingHelper imageProcessingHelper;

    public static Map<String, Employee> employeeMap;
    public static Map<String, Employee> loggedEmployeeMap;

    public EmployeeService()
    {
        if(loggedEmployeeMap == null)
            loggedEmployeeMap = new HashMap<>();

        this.employeeDaoMysql = new EmployeeDaoMysqlImpl();
        this.imageProcessingHelper = new ImageProcessingHelper();
    }

    public Employee getEmployeeByUsernameAndPassword(String username, String password)
    {
        return employeeDaoMysql.getEmployeeByUsernameAndPassword(username, password);
    }

    public void refreshEmployeeMap()
    {
        employeeMap = new HashMap<>();
        List<Employee> employees = getEmployees();

        for(Employee employee : employees)
        {
            employeeMap.put(employee.getId(), employee);
        }
    }

    public Employee addEmployee(String username, String password, String nic, String firstName, String midName, String lastName,
                                String addressLine1, String addressLine2, String addressLine3, String city, String phone, String bloodGroup,
                                String license, File photoFile, String location_id, String vehicle_id)
    {
        byte[] preprocessedPhoto = imageProcessingHelper.preprocess(photoFile);

        Employee employee = new Employee(
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
                bloodGroup,
                license,
                null,
                preprocessedPhoto,
                LocationService.locationMap.get(location_id),
                VehicleService.vehicleMap.get(vehicle_id)
        );

        employee = employeeDaoMysql.addEmployee(employee);
        employee.setPhoto(imageProcessingHelper.decompressBytes(employee.getPhoto()));

        employeeMap.put(employee.getId(), employee);

        return employee;
    }

    public Employee setEmployee(String id, String username, String password, String nic, String firstName, String midName, String lastName,
                                String addressLine1, String addressLine2, String addressLine3, String city, String phone, String bloodGroup,
                                String license, File photoFile, String location_id, String vehicle_id)
    {
        Employee saved = employeeMap.get(id);

        byte[] photo = imageProcessingHelper.compressBytes(saved.getPhoto());

        if(photoFile != null)
            photo = imageProcessingHelper.preprocess(photoFile);

        Employee employee = new Employee(
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
                bloodGroup,
                license,
                saved.getRegisteredDateTime(),
                photo,
                LocationService.locationMap.get(location_id),
                VehicleService.vehicleMap.get(vehicle_id)
        );

        employee = employeeDaoMysql.setEmployee(employee);
        employee.setPhoto(imageProcessingHelper.decompressBytes(employee.getPhoto()));

        employeeMap.put(id, employee);

        return employee;
    }

    public Employee setEmployeeOptional(String id, String username, String password, String addressLine1, String addressLine2, String addressLine3,
                                String city, String phone)
    {
        Employee saved = employeeMap.get(id);

        byte[] photo = imageProcessingHelper.compressBytes(saved.getPhoto());

        Employee employee = new Employee(
                id,
                username,
                password,
                saved.getNic(),
                saved.getFirstName(),
                saved.getMidName(),
                saved.getLastName(),
                addressLine1,
                addressLine2,
                addressLine3,
                city,
                phone,
                saved.getBloodGroup(),
                saved.getLicense(),
                saved.getRegisteredDateTime(),
                photo,
                saved.getLocation(),
                saved.getVehicle()
        );

        employee = employeeDaoMysql.setEmployeeOptional(employee);
        employee.setPhoto(imageProcessingHelper.decompressBytes(employee.getPhoto()));

        employeeMap.put(id, employee);

        return employee;
    }

    public Employee deleteEmployee(String id) {
        Employee employee = EmployeeService.employeeMap.remove(id);
        Employee deleted = employeeDaoMysql.deleteEmployee(employee);

        if(deleted == null)
            EmployeeService.employeeMap.put(employee.getId(), employee);

        return deleted;
    }

    public List<Employee> getEmployees()
    {
        return employeeDaoMysql.getEmployees();
    }

    public Map<String, Employee> getEmployeeMap()
    {
        return employeeMap;
    }

    public void setAsLoggedInEmployee(Employee employee)
    {
        loggedEmployeeMap.put(employee.getId(), employee);
    }

    public void setAsLoggedOutEmployee(Employee employee)
    {
        loggedEmployeeMap.remove(employee.getId());
    }

    public Map<String, Employee> getLoggedEmployeeMap()
    {
        return loggedEmployeeMap;
    }
}