package com.study.controller;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LOGIN_FORM_REQUEST = "login";
    public static final int COOKIE_MAX_AGE = 60 * 4;
    public static final String TOKEN = "token";

}
