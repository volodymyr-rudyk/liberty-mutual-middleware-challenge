package com.middleware.libertymutualmiddlewarechallenge.utils;

import java.security.SecureRandom;

public class GeneratorUtils {
    public static final int DEFAULT_LENGTH = 34;
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz_-";
    private static final SecureRandom random = new SecureRandom();

    // generate random string with predefined length
    public static String generateRandomString(int length) {
        var sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(BASE.length());
            sb.append(BASE.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomString() {
        return generateRandomString(DEFAULT_LENGTH);
    }
}
