package com.cabit.Cab_It.validation;

import com.cabit.Cab_It.helper.nic_data_extract.NewNICDataExtractionHelper;
import com.cabit.Cab_It.helper.nic_data_extract.OldNICDataExtractionHelper;
import com.cabit.Cab_It.model.Employee;
import com.cabit.Cab_It.service.EmployeeService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeValidation
{
    /*
     * Validation class to perform validations related to a employee
     * */
    private final NewNICDataExtractionHelper newNICDataExtractionHelper = new NewNICDataExtractionHelper();
    private final OldNICDataExtractionHelper oldNICDataExtractionHelper = new OldNICDataExtractionHelper();
    private final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    private final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private final Pattern LETTER_PATTERN = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
    private final EmployeeService employeeService = new EmployeeService();

    public Map<String, String> checkFieldInvalidations(String nic,
                                                       String username,
                                                       String password,
                                                       String confirmPassword,
                                                       String firstName,
                                                       String lastName,
                                                       String addressLine1,
                                                       String addressLine2,
                                                       String city,
                                                       String phone,
                                                       String bloodGroup,
                                                       String license)
    {
        Map<String, String> fieldInvalidations = new HashMap<>();

        Matcher usernameSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(username);
        Matcher phoneDigitMatcher = DIGIT_PATTERN.matcher(phone);
        Matcher phoneSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(phone);
        Matcher phoneLetterMatcher = LETTER_PATTERN.matcher(phone);
        Matcher passwordSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(password);
        Matcher passwordDigitCharacterMatcher = DIGIT_PATTERN.matcher(password);
        Matcher passwordLetterCharacterMatcher = LETTER_PATTERN.matcher(password);

        boolean foundDuplicateNICs = false;
        boolean foundDuplicateUsernames = false;
        boolean foundDuplicatePasswords = false;
        boolean foundDuplicatePhones = false;

        for(Employee employee : employeeService.getEmployees())
        {
            if(employee.getNic().equals(nic))
                foundDuplicateNICs = true;
            if(employee.getUsername().equals(username))
                foundDuplicateUsernames = true;
            if(employee.getPassword().equals(password))
                foundDuplicatePasswords = true;
            if(employee.getPhone().equals(phone))
                foundDuplicatePhones = true;
        }


        if(nic.isEmpty())
            fieldInvalidations.put("nic-invalidation", "NIC is required");

        if(!nic.isEmpty() && !(newNICDataExtractionHelper.isValid(nic) || oldNICDataExtractionHelper.isValid(nic)))
            fieldInvalidations.put("nic-invalidation", "non valid NIC");

        if(foundDuplicateNICs)
            fieldInvalidations.put("nic-invalidation", "NIC already exists");

        if(username.isEmpty())
            fieldInvalidations.put("uname-invalidation", "username is required");

        if(!username.isEmpty() && usernameSpecialCharacterMatcher.find())
            fieldInvalidations.put("uname-invalidation", "username contains invalid characters");

        if(!fieldInvalidations.containsKey("nic") && !username.isEmpty() && username.length() < 5)
            fieldInvalidations.put("uname-invalidation", "username must contains 5 or more characters");

        if(foundDuplicateUsernames)
            fieldInvalidations.put("uname-invalidation", "username already exists");

        if(password.isEmpty())
            fieldInvalidations.put("passwd-invalidation", "password is required");

        if(!password.isEmpty() && password.length() < 5)
            fieldInvalidations.put("passwd-invalidation", "password must contains 5 or more characters");

        if(!fieldInvalidations.containsKey("passwd") && !password.isEmpty() && !password.equals(confirmPassword))
            fieldInvalidations.put("confirm-passwd-invalidation", "unmatched password confirmation");

        if(!fieldInvalidations.containsKey("passwd") && !password.isEmpty() && !(passwordDigitCharacterMatcher.find() && passwordLetterCharacterMatcher.find()
                && passwordSpecialCharacterMatcher.find()))
            fieldInvalidations.put("passwd-invalidation", "password must contain at least a letter, number and special character");

        if(foundDuplicatePasswords)
            fieldInvalidations.put("passwd-invalidation", "password already exists");

        if(firstName.isEmpty())
            fieldInvalidations.put("fname-invalidation", "first name is required");

        if(lastName.isEmpty())
            fieldInvalidations.put("lname-invalidation", "last name is required");

        if(addressLine1.isEmpty())
            fieldInvalidations.put("address-1-invalidation", "address line 1 is required");

        if(addressLine2.isEmpty())
            fieldInvalidations.put("address-2-invalidation", "address line 2 is required");

        if(city.isEmpty())
            fieldInvalidations.put("city-invalidation", "city is required");

        if(phone.isEmpty())
            fieldInvalidations.put("phone-invalidation", "phone number is required");

        if(!phone.isEmpty() && phone.length() < 10)
            fieldInvalidations.put("phone-invalidation", "phone number not in given length");

        if(!fieldInvalidations.containsKey("phone") && !phone.isEmpty() && !phoneDigitMatcher.find() ||  (phoneLetterMatcher.find() && phoneSpecialCharacterMatcher.find()))
            fieldInvalidations.put("phone-invalidation", "phone number contains invalid characters");

        if(foundDuplicatePhones)
            fieldInvalidations.put("phone-invalidation", "phone number already exists");

        if(bloodGroup.isEmpty())
            fieldInvalidations.put("blood-group-invalidation", "blood group is required");

        if(license.isEmpty())
            fieldInvalidations.put("license-invalidation", "license is required");

        return fieldInvalidations;
    }

    public Map<String, String> checkFieldInvalidations(String nic,
                                                String username,
                                                String password,
                                                String firstName,
                                                String lastName,
                                                String addressLine1,
                                                String addressLine2,
                                                String city,
                                                String phone,
                                                String bloodGroup,
                                                String license)
    {
        Map<String, String> validationProblems = new HashMap<>();

        Matcher usernameSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(username);
        Matcher phoneDigitMatcher = DIGIT_PATTERN.matcher(phone);
        Matcher phoneSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(phone);
        Matcher phoneLetterMatcher = LETTER_PATTERN.matcher(phone);
        Matcher passwordSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(password);
        Matcher passwordDigitCharacterMatcher = DIGIT_PATTERN.matcher(password);
        Matcher passwordLetterCharacterMatcher = LETTER_PATTERN.matcher(password);

        int duplicatedNICs = 0;
        int duplicateUsernames = 0;
        int duplicatePasswords = 0;
        int duplicatePhones = 0;

        for(Employee employee : employeeService.getEmployees())
        {
            if(employee.getNic().equals(nic))
                duplicatedNICs++;
            if(employee.getUsername().equals(username))
                duplicateUsernames++;
            if(employee.getPassword().equals(password))
                duplicatePasswords++;
            if(employee.getPhone().equals(phone))
                duplicatePhones++;
        }

        if(nic.isEmpty())
            validationProblems.put("nic-invalidation", "NIC is required");

        if(!nic.isEmpty() && !(newNICDataExtractionHelper.isValid(nic) || oldNICDataExtractionHelper.isValid(nic)))
            validationProblems.put("nic-invalidation", "non valid NIC");

        if(duplicatedNICs > 1)
            validationProblems.put("nic-invalidation", "NIC already exists");

        if(username.isEmpty())
            validationProblems.put("uname-invalidation", "username is required");

        if(!username.isEmpty() && usernameSpecialCharacterMatcher.find())
            validationProblems.put("uname-invalidation", "username contains invalid characters");

        if(!validationProblems.containsKey("uname-invalidation") && !username.isEmpty() && username.length() < 5)
            validationProblems.put("uname-invalidation", "username must contains 5 or more characters");

        if(duplicateUsernames > 1)
            validationProblems.put("uname-invalidation", "username already exists");

        if(password.isEmpty())
            validationProblems.put("passwd-invalidation", "password is required");

        if(!password.isEmpty() && password.length() < 5)
            validationProblems.put("passwd-invalidation", "password must contains 5 or more characters");

        if(!validationProblems.containsKey("passwd-invalidation") && !password.isEmpty() && !(passwordDigitCharacterMatcher.find() && passwordLetterCharacterMatcher.find()
                && passwordSpecialCharacterMatcher.find()))
            validationProblems.put("passwd-invalidation", "password must contain at least a letter, number and special character");

        if(duplicatePasswords > 1)
            validationProblems.put("passwd-invalidation", "password already exists");

        if(firstName.isEmpty())
            validationProblems.put("fname-invalidation", "first name is required");

        if(lastName.isEmpty())
            validationProblems.put("lname-invalidation", "last name is required");

        if(addressLine1.isEmpty())
            validationProblems.put("address-1-invalidation", "address line 1 is required");

        if(addressLine2.isEmpty())
            validationProblems.put("address-2-invalidation", "address line 2 is required");

        if(city.isEmpty())
            validationProblems.put("city-invalidation", "city is required");

        if(phone.isEmpty())
            validationProblems.put("phone-invalidation", "phone number is required");

        if(!phone.isEmpty() && phone.length() < 10)
            validationProblems.put("phone-invalidation", "phone number not in given length");

        if(!validationProblems.containsKey("phone-invalidation") && !phone.isEmpty() && !phoneDigitMatcher.find() ||  (phoneLetterMatcher.find() && phoneSpecialCharacterMatcher.find()))
            validationProblems.put("phone-invalidation", "phone number contains invalid characters");

        if(duplicatePhones > 1)
            validationProblems.put("phone-invalidation", "phone number already exists");

        if(bloodGroup.isEmpty())
            validationProblems.put("blood-group-invalidation", "blood group is required");

        if(license.isEmpty())
            validationProblems.put("license-invalidation", "license is required");

        return validationProblems;
    }

    public Map<String, String> checkFieldInvalidations(String username,
                                                       String password,
                                                       String addressLine1,
                                                       String addressLine2,
                                                       String city,
                                                       String phone)
    {
        Map<String, String> validationProblems = new HashMap<>();

        Matcher usernameSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(username);
        Matcher phoneDigitMatcher = DIGIT_PATTERN.matcher(phone);
        Matcher phoneSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(phone);
        Matcher phoneLetterMatcher = LETTER_PATTERN.matcher(phone);
        Matcher passwordSpecialCharacterMatcher = SPECIAL_CHARACTER_PATTERN.matcher(password);
        Matcher passwordDigitCharacterMatcher = DIGIT_PATTERN.matcher(password);
        Matcher passwordLetterCharacterMatcher = LETTER_PATTERN.matcher(password);

        int duplicateUsernames = 0;
        int duplicatePasswords = 0;
        int duplicatePhones = 0;

        for(Employee employee : employeeService.getEmployees())
        {
            if(employee.getUsername().equals(username))
                duplicateUsernames++;
            if(employee.getPassword().equals(password))
                duplicatePasswords++;
            if(employee.getPhone().equals(phone))
                duplicatePhones++;
        }

        if(username.isEmpty())
            validationProblems.put("uname-invalidation", "username is required");

        if(!username.isEmpty() && usernameSpecialCharacterMatcher.find())
            validationProblems.put("uname-invalidation", "username contains invalid characters");

        if(!validationProblems.containsKey("uname-invalidation") && !username.isEmpty() && username.length() < 5)
            validationProblems.put("uname-invalidation", "username must contains 5 or more characters");

        if(duplicateUsernames > 1)
            validationProblems.put("uname-invalidation", "username already exists");

        if(password.isEmpty())
            validationProblems.put("passwd-invalidation", "password is required");

        if(!password.isEmpty() && password.length() < 5)
            validationProblems.put("passwd-invalidation", "password must contains 5 or more characters");

        if(!validationProblems.containsKey("passwd-invalidation") && !password.isEmpty() && !(passwordDigitCharacterMatcher.find() && passwordLetterCharacterMatcher.find()
                && passwordSpecialCharacterMatcher.find()))
            validationProblems.put("passwd-invalidation", "password must contain at least a letter, number and special character");

        if(duplicatePasswords > 1)
            validationProblems.put("passwd-invalidation", "password already exists");

        if(addressLine1.isEmpty())
            validationProblems.put("address-1-invalidation", "address line 1 is required");

        if(addressLine2.isEmpty())
            validationProblems.put("address-2-invalidation", "address line 2 is required");

        if(city.isEmpty())
            validationProblems.put("city-invalidation", "city is required");

        if(phone.isEmpty())
            validationProblems.put("phone-invalidation", "phone number is required");

        if(!phone.isEmpty() && phone.length() < 10)
            validationProblems.put("phone-invalidation", "phone number not in given length");

        if(!validationProblems.containsKey("phone-invalidation") && !phone.isEmpty() && !phoneDigitMatcher.find() ||  (phoneLetterMatcher.find() && phoneSpecialCharacterMatcher.find()))
            validationProblems.put("phone-invalidation", "phone number contains invalid characters");

        if(duplicatePhones > 1)
            validationProblems.put("phone-invalidation", "phone number already exists");

        return validationProblems;
    }
}
