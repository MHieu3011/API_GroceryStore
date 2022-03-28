package com.vcc.apigrocerystore.utils;

public class CommonUtils {

    private CommonUtils() {
    }

    public static boolean checkEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
