package com.cabit.Cab_It.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvertisementValidation
{
    /*
     * Validation class to perform validations related to a advertisement
     * */
    private final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    private final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private final Pattern LETTER_PATTERN = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);

    public Map<String, String> checkFieldInvalidations(String content)
    {
        Matcher contentSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(content);
        Matcher contentDigitMatcher = DIGIT_PATTERN.matcher(content);
        Matcher contentLetterMatcher = LETTER_PATTERN.matcher(content);

        Map<String, String> invalidations = new HashMap<>();

        if(!contentSpecialCharacterMatcher.find() && !contentDigitMatcher.find() && !contentLetterMatcher.find())
            invalidations.put("content-invalidation", "content required");

        else if(content.length() < 10)
            invalidations.put("content-invalidation", "content must contain at least 10 characters");

        return invalidations;
    }
}
