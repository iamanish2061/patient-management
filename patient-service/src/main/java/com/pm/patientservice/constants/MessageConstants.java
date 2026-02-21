package com.pm.patientservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageConstants {

    public static final String SUCCESS_FETCH = "success.fetch";
    public static final String SUCCESS_CREATE = "success.create";
    public static final String SUCCESS_UPDATE = "success.update";
    public static final String SUCCESS_DELETE = "success.delete";


    public static final String EXISTS_ALREADY = "exists.already";
    public static final String NOT_FOUND = "not.found";



    public static final String PATIENT = "patient";
    public static final String EMAIL = "email";
}
