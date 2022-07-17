package com.finance.strategyGeneration.model.creator;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.storage.IndicatorsDescriptionStorage;
import lombok.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class IndicatorsDescriptionStorageCreator {

    public static IndicatorsDescriptionStorage create(@NonNull List<InformationOfIndicator> informationOfIndicators) {
        Assert.notEmpty(informationOfIndicators.stream().filter(Objects::nonNull).toList(), "Коллекция informationOfIndicators не может быть пустой");
        return new IndicatorsDescriptionStorage(informationOfIndicators);
    }

    public static IndicatorsDescriptionStorage create(String... ids) {
        List<InformationOfIndicator> informationOfIndicators = Stream
                .of(ids)
                .map(Long::valueOf)
                .map(id -> InformationOfIndicator.builder().id(id).build())
                .toList();
        return new IndicatorsDescriptionStorage(informationOfIndicators);
    }

    public static IndicatorsDescriptionStorage create() {
        return new IndicatorsDescriptionStorage(List.of());
    }
}
