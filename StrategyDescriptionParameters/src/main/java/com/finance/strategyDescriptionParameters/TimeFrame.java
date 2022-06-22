package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Comparator;
import java.util.List;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TimeFrame {

    M1(1, 30),
    H1(2, 24);

    int order;
    int batchSize;


    public static TimeFrame getMinimalTimeFrame(List<TimeFrame> timeFrames) {
        return timeFrames.stream()
                .min(Comparator.comparing(TimeFrame::getOrder))
                .orElseThrow(() -> new RuntimeException("Не возможно определить минимальный TimeFrame из переданной коллекции: " + timeFrames));
    }
}
