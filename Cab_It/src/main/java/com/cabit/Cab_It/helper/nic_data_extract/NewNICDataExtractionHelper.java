package com.cabit.Cab_It.helper.nic_data_extract;

import com.cabit.Cab_It.helper.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewNICDataExtractionHelper implements NICDataExtractor
{
    /*
        Helper class which extracts details such as dob, gender from nic number
        NOTE: this class only ables to extract details of nic registered on or after 2016(New NIC cards only)
     */
    private final int NIC_CHARACTER_LENGTH = 12;
    private final int[] BORN_YEAR_POS_START_END = {0, 3};
    private final int[] DAY_OF_YEAR_POS_START_END = {4, 6};

    @Override
    public boolean isValid(String nic) {
        if(nic == null || nic.length() != NIC_CHARACTER_LENGTH)
            return false;

        Pattern nicPattern = Pattern.compile("[0-9]+");
        Matcher nicMatcher = nicPattern.matcher(nic);

        if(!nicMatcher.matches())
            return false;

        int dayOfYear = Integer.parseInt(
                nic.substring(DAY_OF_YEAR_POS_START_END[0], DAY_OF_YEAR_POS_START_END[1] + 1)
        );

        return !(dayOfYear == 500 || dayOfYear > 866 || dayOfYear <= 0);
    }

    @Override
    public LocalDate getBirthday(String nic) {
        if(!isValid(nic))
            return null;
        String year = nic.substring(BORN_YEAR_POS_START_END[0],
                BORN_YEAR_POS_START_END[1] + 1);

        int dayOfYear = Integer.parseInt(nic.substring(DAY_OF_YEAR_POS_START_END[0],
                DAY_OF_YEAR_POS_START_END[1] + 1));

        if(dayOfYear > 500)
            dayOfYear -= 500;

        for(Integer month : SIDEREAL_YEAR_CALENDAR_DAY_SUMS.keySet())
        {
            int[] range = SIDEREAL_YEAR_CALENDAR_DAY_SUMS.get(month);
            int start = range[0];
            int end = range[1];

            if(dayOfYear >= start && dayOfYear <= end)
            {
                int date = dayOfYear - (start - 1);

                if(isLeapYear(year))
                    date--;

                String formattedBirthdayStr =
                        DateTimeFormatter.ISO_LOCAL_DATE.format(
                                LocalDate.of(Integer.parseInt(year), month, date));

                return LocalDate.parse(formattedBirthdayStr);
            }
        }
        return null;
    }

    @Override
    public Gender getGender(String nic) {
        int dayOfYear = Integer.parseInt(nic.substring(DAY_OF_YEAR_POS_START_END[0],
                DAY_OF_YEAR_POS_START_END[1] + 1));

        if(dayOfYear > 500)
            return Gender.FEMALE;
        return Gender.MALE;
    }
}
