package com.finance.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RowOfCandleMonitoring {

    public static final List<String> WEEKENDS = List.of(
            "20220604", "20220605",
            "20220611", "20220612",
            "20220618", "20220619",
            "20220625", "20220626",
            "20220630", "20220631"
    );

    @SneakyThrows
    public static void main(String[] args) {

//            String filePath = "M1/EURUSD_220601_220630";
//            String newFilePath = "M1/EURUSD_220601_220630_NEW";
//            Integer numberElements = 1440;

        String filePath = "H1/EURUSD_220601_220630";
        String newFilePath = "H1/EURUSD_220601_220630_NEW";
        Integer numberElements = 24;

        File file = new File(
                "/home/maxim/Yandex.Disk/algotrading/data/candles-data/" + filePath + ".txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);


        List<String> linesWithOutWeekends =
                Stream
                        .iterate(getLine(reader), Objects::nonNull, s -> getLine(reader))
                        .filter(line -> WEEKENDS.stream().noneMatch(line::contains))
                        .toList();

        // M1 linesWithOutWeekends size=30240
        // H1 linesWithOutWeekends size=504*60=30240
        System.out.println("linesWithOutWeekends size=" + linesWithOutWeekends.size());


        Map<String, Integer> countLinesMap = linesWithOutWeekends
                .stream()
                .collect(Collectors.toMap(RowOfCandleMonitoring::createKey, s -> 1, Integer::sum));

        System.out.println(countLinesMap);
        System.out.println("size: " + countLinesMap.entrySet().size());
        System.out.println(countLinesMap.entrySet().stream().map(Map.Entry::getKey).toList());


        // M1 size: 22 [20220609, 20220608, 20220629, 20220607, 20220628, 20220617, 20220606, 20220627, 20220616, 20220615, 20220614, 20220603, 20220624, 20220613, 20220602, 20220623, 20220601, 20220622, <DATE>, 20220621, 20220610, 20220620]
        // H1 size: 22 [20220609, 20220608, 20220629, 20220607, 20220628, 20220617, 20220606, 20220627, 20220616, 20220615, 20220614, 20220603, 20220624, 20220613, 20220602, 20220623, 20220601, 20220622, <DATE>, 20220621, 20220610, 20220620]

    }

    private static String getDay(String line) {
        return line.split(";")[2];
    }

    private static String getTime(String line) {
        return line.split(";")[3];
    }

    private static String createKey(String line) {
        return line.split(";")[2];
    }

    private static String getLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (Exception exception) {
            return null;
        }
    }
}
