package com.finance.createIndicatorData.service.converterToDataOfIndicator;

public class BinaryInteger {
    public static String restoreBinarySize(int batchSize, String binaryString) {
        if (binaryString.length() < batchSize) {
            return "0".repeat(batchSize - binaryString.length()) + binaryString;
        }
        return binaryString;
    }

}
