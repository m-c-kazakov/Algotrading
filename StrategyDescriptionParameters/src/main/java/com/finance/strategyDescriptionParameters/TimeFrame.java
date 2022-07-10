package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
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
        try {
            // TODO поправ
            Assert.notEmpty(timeFrames, "Коллекция TimeFrames не может быть пустой.");
            Assert.isTrue(timeFrames.stream().noneMatch(Objects::isNull),
                    "Коллекция TimeFrames не может содержать null элементы.");


            return timeFrames.stream()
                    .distinct()
                    .map(TimeFrame::getPer)
                    .min(Integer::compare)
                    .map(TimeFrame::getTimeFrameByPer)
                    .orElseThrow(() -> new RuntimeException(
                            "Не возможно определить минимальный TimeFrame из переданной коллекции: " + timeFrames));
        } catch (Exception exception) {
            return TimeFrame.M1;
        }
    }

    public static TimeFrame getRandomTimeFrame() {
        List<TimeFrame> timeFrames = getTimeFrames();
        return timeFrames.get(ThreadLocalRandom.current()
                .nextInt(timeFrames.size()));
    }
}
