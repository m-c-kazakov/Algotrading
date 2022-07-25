package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.storage.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import lombok.*;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Value
@Table("information_of_indicator")
public final class InformationOfIndicator {

    @Id
    @With
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NonFinal
    String hashCode;
    IndicatorType indicatorType;
    InformationOfCandlesStorage informationOfCandles;
    IndicatorParametersConfigurationStorage parameters;


    @Builder(builderMethodName = "builder", toBuilder = true)
    public static InformationOfIndicator newInformationOfIndicator(Long id, IndicatorType indicatorType,
                                                                   InformationOfCandlesStorage informationOfCandles,
                                                                   IndicatorParametersConfigurationStorage parameters) {
        String uniqueIdentifier = null;
        if (nonNull(indicatorType) && nonNull(informationOfCandles) && nonNull(parameters)) {
            uniqueIdentifier =
                    String.join("_", indicatorType.name(), informationOfCandles.toString(), parameters.toString());
        }

        return new InformationOfIndicator(id, uniqueIdentifier, indicatorType, informationOfCandles, parameters);
    }

    public String getHashCode() {
        if (nonNull(indicatorType) && nonNull(informationOfCandles) && nonNull(parameters)) {
            this.hashCode =
                    String.join("_", indicatorType.name(), informationOfCandles.toString(), parameters.toString());
        } else {
            throw new RuntimeException(
                    "Один из объектов для формирования uniqueIdentifier/hashCode = null; indicatorType=%s; informationOfCandles=%s; parameters=%s; ".formatted(
                            this.indicatorType, this.informationOfCandles, this.parameters));
        }

        return hashCode;
    }

    public String receiveStringId() {
        return String.valueOf(id);
    }

    public Map<String, String> receiveParameters() {
        return new HashMap<>(this.getParameters().getParameters());
    }

    public TimeFrame receiveTimeFrame() {
        return informationOfCandles.receiveTimeFrame();
    }

    public CurrencyPair receiveCurrencyPair() {
        return informationOfCandles.receiveCurrencyPair();
    }

    public Long receiveInformationOfCandlesId() {
        return informationOfCandles.receiveId();
    }

    public InformationOfIndicator withInformationOfCandles(InformationOfCandlesStorage informationOfCandles) {

        return InformationOfIndicator
                .builder()
                .id(this.id)
                .indicatorType(this.indicatorType)
                .informationOfCandles(informationOfCandles)
                .parameters(this.parameters)
                .build();
    }

    public InformationOfIndicator withParameters(IndicatorParametersConfigurationStorage parameters) {
        return InformationOfIndicator
                .builder()
                .id(this.id)
                .indicatorType(this.indicatorType)
                .informationOfCandles(this.informationOfCandles)
                .parameters(parameters)
                .build();
    }
}
