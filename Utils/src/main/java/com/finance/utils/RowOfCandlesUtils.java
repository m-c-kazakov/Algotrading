package com.finance.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class RowOfCandlesUtils {


    public static final List<String> WEEKENDS = List.of(
            "20220604", "20220605",
            "20220611", "20220612",
            "20220618", "20220619",
            "20220625", "20220626",
            "20220630", "20220631",
            "20220701"
    );


    public static void main(String[] args) {
        try {
            String filePath = "M1/EURUSD_220601_220630 (копия)";
            String newFilePath = "M1/EURUSD_220601_220630";
            Integer numberElements = 1440;

//            String filePath = "H1/EURUSD_220601_220630 (копия)";
//            String newFilePath = "H1/EURUSD_220601_220630";
//            Integer numberElements = 24;

            File file = new File(
                    "/home/maxim/Yandex.Disk/algotrading/data/candles-data/" + filePath + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);


            List<String> linesWithOutWeekends =
                    Stream
                            .iterate(getLine(reader), Objects::nonNull, s -> getLine(reader))
                            .filter(line -> WEEKENDS.stream().noneMatch(line::contains))
                            .collect(Collectors.toCollection(ArrayList::new));


            Map<String, Integer> countLinesMap = linesWithOutWeekends
                    .stream()
                    .collect(Collectors.toMap(RowOfCandlesUtils::createKey, s -> 1, Integer::sum));

            System.out.println("countLinesMap="+countLinesMap);

            List<String> timesForOneDay = countLinesMap
                    .entrySet()
                    .stream()
                    .filter(stringIntegerEntry -> Objects.equals(stringIntegerEntry.getValue(), numberElements))
                    .findFirst().map(Map.Entry::getKey)
                    .map(rowData -> linesWithOutWeekends
                            .stream()
                            .filter(line -> line.contains(rowData))
                            .map(RowOfCandlesUtils::getTime).toList())
                    .orElseThrow(() -> new RuntimeException("Не найдены значения с нужным количеством элементов"));

            Assert.state(timesForOneDay.size() == numberElements, "Количество элементов должно быть="+numberElements);

//            System.out.println(timesForOneDay);

            String currentDay = getDay(linesWithOutWeekends.get(0));
            Integer timeIndex = 0;
            for (int index = 1; index < linesWithOutWeekends.size(); index++) {
                String line = linesWithOutWeekends.get(index);
                String time = getTime(line);
                String day = getDay(line);
                if (!Objects.equals(day, currentDay)) {
                    currentDay = day;
                    timeIndex = 0;
                }

                changeList(linesWithOutWeekends, timesForOneDay, index, timeIndex, currentDay);


                timeIndex++;

            }

            System.out.println(linesWithOutWeekends
                    .stream()
                    .collect(Collectors.toMap(RowOfCandlesUtils::createKey, s -> 1, Integer::sum)));

            File newFile = new File(
                    "/home/maxim/Yandex.Disk/algotrading/data/candles-data/" + newFilePath + ".txt");


            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));) {
                linesWithOutWeekends.forEach(s -> {
                    writeValue(writer, s);
                    writeValue(writer, "\n");
                });
            } catch (Exception exception) {
                log.error(exception.getMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private static void writeValue(FileWriter fw, String s) {
        fw.write(s);
    }

    @SneakyThrows
    private static void writeValue(BufferedWriter writer, String value) {
        writer.write(value);
    }

    public static void changeList(List<String> linesWithOutWeekends, List<String> timesForOneDay, Integer index, Integer timeIndex, String currentDay) {
        String line = linesWithOutWeekends.get(index);
        String time = getTime(line);
        String day = getDay(line);

        if (!Objects.equals(timesForOneDay.get(timeIndex), time)) {
            String previousLineDay = getDay(linesWithOutWeekends.get(index - 1));
            if (index == 1 || !Objects.equals(previousLineDay, currentDay)) {
                String currentLineWithReplaceTime = replaceTime(line, time, timesForOneDay.get(timeIndex));
                linesWithOutWeekends.add(index, currentLineWithReplaceTime);

            } else {
                String currentLineWithReplaceTime = replaceTime(line, time, timesForOneDay.get(timeIndex));
                linesWithOutWeekends.add(index, currentLineWithReplaceTime);

            }
        }



    }

    private static String replaceTime(String line, String currentTime, String needTime) {
        return line.replace(currentTime, needTime);
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
            log.error("Ошибка при получении строки из файла", exception);
            return null;
        }
    }
}
