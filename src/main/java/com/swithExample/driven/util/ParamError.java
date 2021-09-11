package com.swithExample.driven.util;


public interface ParamError {

    String FIELD_NAME = "{fieldName} is ${validatedValue == null ? 'null' : 'empty'}";

    String MAX_LENGTH = "Maximum length {fieldName} is {max} characters";

    String MIN_VALUE = "Minimum {fieldName} is {value}";

    String MAX_VALUE = "Maximum {fieldName} is {value}";

    String MIN_NUMBER = "{fieldName} should not be less than {value}";

    String DEFAULT = "{fieldName} default is {value}";

    String MAX_SELECTION = "Maximum Created selection option is {max}";
}
