package com.filipetavares.tests.core;

public class Utils {
        public static String expirationDateConverter(String date) {
            return date.substring(0, 2) + "/" + date.substring(2);
        }
    }
