package com.pm.patientservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessage {

    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String ADDRESS = "Address";
    public static final String DATE_OF_BIRTH = "Date of birth";


    public static final String INVALID = " is invalid";
    public static final String REQUIRED = " is required";
    public static final String LENGTH_SHORT = " must be more than 8 characters";
    public static final String LENGTH_100 = " must not exceed 100 characters";
    public static final String LENGTH_255 = " must not exceed 255 characters";


}
