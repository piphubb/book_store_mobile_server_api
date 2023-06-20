package com.piphub.springjwtauthentication.caches;

import java.util.HashMap;
import java.util.Map;


public class SmsCache {

    private static Map<String, String> otpCaches;

    public static void init() {
        otpCaches = new HashMap<>();
    }

    public static void add(String key, String value) {
        otpCaches.put(key, value);
    }

    public static void remove(String key) {
        otpCaches.remove(key);
    }

    public static String get(String key) {
        return otpCaches.get(key);
    }
}

