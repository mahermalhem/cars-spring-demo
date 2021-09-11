package com.swithExample.driven.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**/
public class AppUtil {

    public static final Random RANDOM = new SecureRandom();
    /**
     * Generate Salt for the password
     */
    public static String generateSalt() {
        byte[] salt = new byte[Constant.SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
