package com.cabit.Cab_It.helper;

public enum Gender
{
    /*
    * An enum to maintain genders related to employees and customers
    * */

    MALE("male"),
    FEMALE("female");

    private String sex;

    Gender(String sex) {
        this.sex = sex;
    }
}
