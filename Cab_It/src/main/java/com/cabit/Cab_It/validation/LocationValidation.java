package com.cabit.Cab_It.validation;

import java.util.HashMap;
import java.util.Map;

public class LocationValidation
{
    /*
     * Validation class to perform validations related to a location
     * */
    public Map<String, String> checkFieldInvalidations(String zone)
    {
        Map<String, String> invalidations = new HashMap<>();

        if(zone.isEmpty())
            invalidations.put("zone-invalidation", "zone is required");

        else if(zone.length() < 5)
            invalidations.put("zone-invalidation", "Zone must contain 5 or more characters");

        return invalidations;
    }
}
