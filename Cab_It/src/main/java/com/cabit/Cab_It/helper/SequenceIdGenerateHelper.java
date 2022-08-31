package com.cabit.Cab_It.helper;

public class SequenceIdGenerateHelper
{
    /*
     * Helper class to generate sequence ids for table primary keys
     * */
    private final String EMPLOYEE_ID_TEMPLATE = "EMP_%05d";
    private final String VEHICLE_ID_TEMPLATE = "VEH_%05d";
    private final String LOCATION_ID_TEMPLATE = "LOC_%05d";
    private final String ADVERTISEMENT_ID_TEMPLATE = "ADV_%05d";
    private final String CUSTOMER_ID_TEMPLATE = "CUS_%05d";

    public String generateEmployeeId(int val)
    {
        return String.format(EMPLOYEE_ID_TEMPLATE, val);
    }

    public String generateVehicleId(int val)
    {
        return String.format(VEHICLE_ID_TEMPLATE, val);
    }

    public String generateLocationId(int val)
    {
        return String.format(LOCATION_ID_TEMPLATE, val);
    }

    public String generateAdvertisementId(int val)
    {
        return String.format(ADVERTISEMENT_ID_TEMPLATE, val);
    }
    public String generateCustomerId(int val)
    {
        return String.format(CUSTOMER_ID_TEMPLATE, val);
    }
}
