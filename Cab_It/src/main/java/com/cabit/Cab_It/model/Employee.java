package com.cabit.Cab_It.model;

import com.cabit.Cab_It.helper.Gender;
import com.cabit.Cab_It.helper.nic_data_extract.NewNICDataExtractionHelper;
import com.cabit.Cab_It.helper.nic_data_extract.OldNICDataExtractionHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Employee
{
    /*
     * Basic POJO class for employee
     * */
    private String id;
    private String username;
    private String password;
    private String nic;
    private String firstName;
    private String midName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String phone;
    private String bloodGroup;
    private String license;
    private LocalDateTime registeredDateTime;
    private byte[] photo;
    private Location location;
    private Vehicle vehicle;
    private final Role ROLE = Role.EMPLOYEE;

    //Derived attributes
    private Gender gender;
    private LocalDate dob;
    private int ageInYears;
    private Period serviceTime;

    // Access privilege controlling data
    public static final Map<String, Set<Role>> CRUD_PRIVILEGES = new HashMap<>()
    {
        {
            put("create", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to insert an employee
            put("read", Set.of(
                    Role.ADMIN, Role.EMPLOYEE
            )); //Defines which role(s) could be able to access an employee
            put("update", Set.of(
                    Role.ADMIN, Role.EMPLOYEE
            )); //Defines which role(s) could be able to update an employee
            put("delete", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to delete an employee
        }
    };

    public Employee(String username, String password, String nic, String firstName, String midName, String lastName, String addressLine1, String addressLine2, String addressLine3, String city, String phone, String bloodGroup, String license, LocalDateTime registeredDateTime, byte[] photo, Location location, Vehicle vehicle) {
        this.username = username;
        this.password = password;
        this.nic = nic;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.city = city;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.license = license;
        this.registeredDateTime = registeredDateTime;
        this.photo = photo;
        this.location = location;
        this.vehicle = vehicle;
    }

    public Employee(String id, String username, String password, String nic, String firstName, String midName,
                    String lastName, String addressLine1, String addressLine2, String addressLine3, String city, String phone,
                    String bloodGroup, String license, LocalDateTime registeredDateTime, byte[] photo, Location location, Vehicle vehicle) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nic = nic;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.city = city;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.license = license;
        this.registeredDateTime = registeredDateTime;
        this.photo = photo;
        this.location = location;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public LocalDateTime getRegisteredDateTime() {
        return registeredDateTime;
    }

    public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
        this.registeredDateTime = registeredDateTime;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Role getROLE() {
        return ROLE;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setServiceYears() {
        this.serviceTime = Period.between(
                LocalDate.of(
                        this.registeredDateTime.getYear(),
                        this.registeredDateTime.getMonth(),
                        this.registeredDateTime.getDayOfMonth()
                ),
                LocalDate.now()
        );
    }

    public void setAgeInYears() {
        this.ageInYears =Period.between(
                this.dob,
                LocalDate.now()
        ).getYears();
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public Period getServiceTime() {
        return serviceTime;
    }

    public void setDerivedAttributes()
    {
        NewNICDataExtractionHelper newNICDataExtractionHelper = new NewNICDataExtractionHelper();
        OldNICDataExtractionHelper oldNICDataExtractionHelper = new OldNICDataExtractionHelper();

        if(newNICDataExtractionHelper.isValid(this.nic))
        {
            this.setDob(newNICDataExtractionHelper.getBirthday(this.nic));
            this.setGender(this.gender = newNICDataExtractionHelper.getGender(this.nic));
        }
        else if(oldNICDataExtractionHelper.isValid(this.nic))
        {
            this.setDob(oldNICDataExtractionHelper.getBirthday(this.nic));
            this.setGender(oldNICDataExtractionHelper.getGender(this.nic));
        }

        setAgeInYears();
        setServiceYears();
    }

    public static Map<String, Set<Role>> getCrudPrivileges() {
        return CRUD_PRIVILEGES;
    }

    @Override
    public String toString() {
        this.setDerivedAttributes();

        return "Employee{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nic='" + nic + '\'' +
                ", firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", license='" + license + '\'' +
                ", registeredDateTime=" + registeredDateTime +
                ", location=" + location +
                ", vehicle=" + vehicle +
                ", ROLE=" + ROLE +
                ", gender=" + gender +
                ", dob=" + dob +
                ", ageInYears=" + ageInYears +
                ", serviceYears=" + serviceTime.getYears() +
                ", serviceMonths=" + serviceTime.getMonths() +
                ", serviceDays=" + serviceTime.getDays() +
                '}';
    }
}