package com.piphub.springjwtauthentication.utils;


public class EmailUtil {
	
    public static boolean isEmail(String input) {

        if (null == input)
            return false;

        if (input.indexOf("@") > 0) {
            return true;
        }

        return false;
    }
	
	
}
