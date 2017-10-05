package com.example.at.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final int WAIT_SMALL = ApplicationProperties.getInteger(ApplicationProperties.ApplicationProperty.WAIT_TIMEOUT_SHT);
    public static final int WAIT_NORMAL = ApplicationProperties.getInteger(ApplicationProperties.ApplicationProperty.WAIT_TIMEOUT);
    public static final int WAIT_LONG = ApplicationProperties.getInteger(ApplicationProperties.ApplicationProperty.WAIT_TIMEOUT_LNG);

    public static final String FIRST_FORM = "formProductInfo";
    public static final String SECOND_FORM = "formUwQuestions";
    public static final String THIRD_FORM = "form3";

    public static final String ACTION_NEXT = "next";
    public static final String ACTION_SAVE = "save";
    public static final String ACTION_NOT_INTERESTED = "notInterested";
    public static final String ACTION_SUBMIT = "submit";
    public static final String ACTION_PREVIOUS = "previous";
}