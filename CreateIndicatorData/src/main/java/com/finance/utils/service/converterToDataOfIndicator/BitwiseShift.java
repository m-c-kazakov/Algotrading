package com.finance.utils.service.converterToDataOfIndicator;

public class BitwiseShift {


    public static int addOne(int value) {
        return value << 1 | 1;
    }

    public static int addZero(int value) {
        return value << 1;
    }
}
