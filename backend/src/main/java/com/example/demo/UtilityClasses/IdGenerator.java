package com.example.demo.UtilityClasses;

import java.security.SecureRandom;
import java.util.UUID;

public class IdGenerator {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random=new SecureRandom();

    public static String generateEmployeeID(){
        return "EMP"+randomString(7);
    }

    public static String generateProductLineID(){
        return "PL"+randomString(7);
    }

    public static String generateOfficeID(){
        return "OF"+randomString(5);
    }

    public static String generateProductID(){
        return "P"+randomString(5);
    }

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }



}
