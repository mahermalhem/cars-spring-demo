package com.swithExample.driven.util;

/**
 * @author Phil Conal
 */
public interface Constant {

    String HEADER_TOKEN = "Auth-Token";
    // API format date
    String API_FORMAT_DATE_TIME = "dd/MM/yyyy hh:mm:ss a";
    String API_FORMAT_DATE = "MM/dd/yyyy";

    String VALID_XSS = "^((?!<|>)[\\s\\S])*?$";
    String VALID_CURLY_BRACES = "^((?!\\{|\\})[\\s\\S])*?$";

    int SALT_LENGTH = 6;

}


