package com.vcc.apigrocerystore.utils;

public class CommonUtils {

    private CommonUtils() {
    }

    public synchronized static boolean checkEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }
}
