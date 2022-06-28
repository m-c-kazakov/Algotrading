package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TimeFrame {

    M1(1, 30),
    H1(60, 24);

    int per;
    int batchSize;

    public static TimeFrame[] getTimeFrames() {
        return TimeFrame.values();
    }

    public static TimeFrame getTimeFrameByPer(int per) {
        return Arrays.stream(getTimeFrames()).filter(timeFrame -> timeFrame.getPer() == per).findFirst()
                .orElseThrow(() -> new RuntimeException("Не возможно определить TimeFrame по per==" + per));
    }

    public static TimeFrame getMinimalTimeFrame(List<TimeFrame> timeFrames) {
        return timeFrames.stream()
                .min(Comparator.comparing(TimeFrame::getPer))
                .orElseThrow(() -> new RuntimeException("Не возможно определить минимальный TimeFrame из переданной коллекции: " + timeFrames));
    }



}
