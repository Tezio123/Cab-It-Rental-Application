package com.cabit.Cab_It.validation;

import com.cabit.Cab_It.model.Vehicle;
import com.cabit.Cab_It.service.VehicleService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleValidation
{
    /*
     * Validation class to perform validations related to a vehicle
     * */
    private final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    private final Pattern LETTER_PATTERN = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
    private final VehicleService vehicleService = new VehicleService();

    public Map<String, String> checkFieldInvalidations(String model,
                                                     String brand,
                                                     String plateNumber,
                                                     String topSpeed)
    {
        Matcher topSpeedSpecialCharMatcher = SPECIAL_CHARACTER_PATTERN.matcher(topSpeed);
        Matcher topSpeedLetterMatcher = LETTER_PATTERN.matcher(topSpeed);

        Map<String, String> invalidations = new HashMap<>();
        
        for(Vehicle vehicle : vehicleService.getVehicleMap().values())
        {
            if(vehicle.getPlateNumber().equals(plateNumber))
            {
            	invalidations.put("plate-num-invalidation", "plate number already exists");
                break;
            }
        }
        
        if(model.isEmpty())
            invalidations.put("model-invalidation", "model is required");
        if(brand.isEmpty())
            invalidations.put("brand-invalidation", "brand is required");
        if(plateNumber.isEmpty())
            invalidations.put("plate-num-invalidation", "plate number is required");
        if(topSpeed.isEmpty())
            invalidations.put("top-speed-invalidation", "top speed is required");
        if(!topSpeed.isEmpty() && (topSpeedSpecialCharMatcher.find() || topSpeedLetterMatcher.find()))
            invalidations.put("top-speed-invalidation", "top speed not in given format");

        return invalidations;
    }
    
    public Map<String, String> checkFieldInvalidationsForUpdate(String model,
											            String brand,
											            String plateNumber,
											            String topSpeed)
{
		Matcher topSpeedSpecialCharMatcher = SPECIAL_CHARACTER_PATTERN.matcher(topSpeed);
		Matcher topSpeedLetterMatcher = LETTER_PATTERN.matcher(topSpeed);
		
		int plateNumbers = 0;
		
		Map<String, String> invalidations = new HashMap<>();
		
		for(Vehicle vehicle : vehicleService.getVehicleMap().values())
		{
			if(vehicle.getPlateNumber().equals(plateNumber))
			{ 
				if(++plateNumbers >= 2)
				{
					invalidations.put("plate-num-invalidation", "plate number already exists");
					break;
				}
			}
		}
		
		if(model.isEmpty())
			invalidations.put("model-invalidation", "model is required");
		if(brand.isEmpty())
			invalidations.put("brand-invalidation", "brand is required");
		if(plateNumber.isEmpty())
			invalidations.put("plate-num-invalidation", "plate number is required");
		if(topSpeed.isEmpty())
			invalidations.put("top-speed-invalidation", "top speed is required");
		if(!topSpeed.isEmpty() && (topSpeedSpecialCharMatcher.find() || topSpeedLetterMatcher.find()))
			invalidations.put("top-speed-invalidation", "top speed not in given format");
		
		return invalidations;
}
}
