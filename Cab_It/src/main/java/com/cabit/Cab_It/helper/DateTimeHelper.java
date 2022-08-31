package com.cabit.Cab_It.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper
{
    /*
    * Helper class to perform operations related to date and time
    * */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public LocalDateTime getFormattedDateTime(String rawDateTime, String pattern)
    {
        if(rawDateTime == null || rawDateTime.isEmpty() || rawDateTime.equals("null"))
            return null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(rawDateTime, dateTimeFormatter);

        return localDateTime;
    }

    public LocalDateTime getFormattedDateTime(String rawDateTime)
    {
        if(rawDateTime == null || rawDateTime.isEmpty() || rawDateTime.equals("null"))
            return null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse(rawDateTime, dateTimeFormatter);

        return localDateTime;
    }

    public LocalDateTime getDateTime()
    {
        String dateTimeStr = LocalDateTime.now().toString();

        return getFormattedDateTime(dateTimeStr
                .substring(0, 10)
                .concat(" ")
                .concat(dateTimeStr.substring(11, 19))
        );
    }

    public String formatDateTimeStr(String dateTimeStr)
    {
        return dateTimeStr
                .substring(0, 10)
                .concat(" ")
                .concat(dateTimeStr.substring(11));
    }
}
