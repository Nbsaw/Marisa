package com.nbsaw.morisa.kit;

public class Assert {

    public static void smallerThan(double num, double exp, String msg) {
        if (num < exp) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void biggerThan(double num, double exp, String msg) {
        if (num > exp) {
            throw new IllegalArgumentException(msg);
        }
    }

}
