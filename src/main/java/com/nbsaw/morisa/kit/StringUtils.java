package com.nbsaw.morisa.kit;

public class StringUtils {
    public static String rightPad(String s, int length) {
        if (s.length() >= length)
            return s;
        else {
            int l = length - s.length();
            while (l-- > 0) {
                s += " ";
            }
            return s;
        }
    }
}
