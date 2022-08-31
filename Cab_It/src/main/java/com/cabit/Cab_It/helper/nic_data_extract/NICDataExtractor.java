package com.cabit.Cab_It.helper.nic_data_extract;

import com.cabit.Cab_It.helper.Gender;

import java.time.LocalDate;
import java.util.Map;

public interface NICDataExtractor
{
    /*
        Interface class to implement methods which extracts details such as dob,
        gender from nic number
     */
    Map<Integer, int[]> SIDEREAL_YEAR_CALENDAR_DAY_SUMS = Map.ofEntries(
            Map.entry(1, new int[]{1, 31}),
            Map.entry(2, new int[]{32, 59}),
            Map.entry(3, new int[]{60, 90}),
            Map.entry(4, new int[]{91, 120}),
            Map.entry(5, new int[]{121, 151}),
            Map.entry(6, new int[]{152, 181}),
            Map.entry(7, new int[]{182, 212}),
            Map.entry(8, new int[]{213, 243}),
            Map.entry(9, new int[]{244, 273}),
            Map.entry(10, new int[]{274, 304}),
            Map.entry(11, new int[]{305, 334}),
            Map.entry(12, new int[]{335, 365})

    );

    default boolean isLeapYear(String year)
    {
        int yearVal = Integer.parseInt(year);

        return yearVal % 4 == 0;
    }

    boolean isValid(String nic);
    LocalDate getBirthday(String nic);
    Gender getGender(String nic);
}
