package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TimeFrame {

    M1(1, 30),
    H1(60, 24);

    int per;
    int batchSize;

    public static List<TimeFrame> getTimeFrames() {
        return List.of(TimeFrame.values());
    }

    public static TimeFrame getTimeFrameByPer(int per) {
        return getTimeFrames().stream().filter(timeFrame -> timeFrame.getPer() == per).findFirst()
                .orElseThrow(() -> new RuntimeException("Не возможно определить TimeFrame по per==" + per));
    }

    public static TimeFrame getMinimalTimeFrame(List<TimeFrame> timeFrames) {
        return timeFrames.stream()
                .min(Comparator.comparing(TimeFrame::getPer))
                .orElseThrow(() -> new RuntimeException("Не возможно определить минимальный TimeFrame из переданной коллекции: " + timeFrames));
    }

    public static TimeFrame getRandomTimeFrame() {
        List<TimeFrame> timeFrames = getTimeFrames();
        return timeFrames.get(ThreadLocalRandom.current()
                .nextInt(timeFrames.size()));
    }
}
