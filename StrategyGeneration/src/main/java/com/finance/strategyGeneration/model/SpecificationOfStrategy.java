package com.finance.strategyGeneration.model;

import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.model.storage.ConfigurationStorage;
import com.finance.strategyGeneration.model.storage.IndicatorsDescriptionStorage;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@With
@Getter
@Builder
@ToString
@RequiredArgsConstructor
@Table("specification_of_strategy")
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategy {

    @Id
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Long statisticsOfStrategyId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    String hashCode;
    @NotNull
    Long startScore;
    @NotNull
    Integer acceptableRisk;
    @NotBlank
    SumOfDealType sumOfDealType;
    @NotEmpty
    ConfigurationStorage<SumOfDealConfigurationKey> sumOfDealConfigurationData;
    @NotBlank
    StopLossType stopLossType;
    @NotEmpty
    ConfigurationStorage<StopLossConfigurationKey> stopLossConfigurationData;
    @NotBlank
    TrailingStopType trailingStopType;
    @NotEmpty
    ConfigurationStorage<TrailingStopConfigurationKey> trailingStopConfigurationData;
    @NotBlank
    TakeProfitType takeProfitType;
    @NotEmpty
    ConfigurationStorage<TakeProfitConfigurationKey> takeProfitConfigurationData;
    @NotBlank
    TypeOfDeal typeOfDeal;

    @NotBlank
    InformationOfCandlesStorage informationOfCandles;
    @NotNull
    IndicatorsDescriptionStorage descriptionToOpenADeal;
    @NotNull
    IndicatorsDescriptionStorage descriptionToCloseADeal;

    public List<InformationOfIndicator> getDescriptionToCloseADealIndicators() {
        return this.descriptionToCloseADeal.getInformationOfIndicators();
    }

    public List<InformationOfIndicator> getDescriptionToOpenADealIndicators() {
        return this.descriptionToOpenADeal.getInformationOfIndicators();
    }

    public SpecificationOfStrategy withDescriptionToCloseADealIndicators(List<InformationOfIndicator> descriptionToCloseADeal) {

        IndicatorsDescriptionStorage indicatorsDescriptionStorage =
                descriptionToCloseADeal.isEmpty() ?
                        IndicatorsDescriptionStorageCreator.create() :
                        IndicatorsDescriptionStorageCreator.create(descriptionToCloseADeal);
        return this.withDescriptionToCloseADeal(indicatorsDescriptionStorage);
    }

    public SpecificationOfStrategy withDescriptionToOpenADealIndicators(List<InformationOfIndicator> descriptionToOpenADeal) {
        Assert.notEmpty(descriptionToOpenADeal, "Коллекция индикаторов descriptionToOpenADeal не может быть пустой");
        return this.withDescriptionToOpenADeal(IndicatorsDescriptionStorageCreator.create(descriptionToOpenADeal));
    }

    public List<String> getDescriptionToOpenADealStringIds() {
        return this.descriptionToOpenADeal.getStringIds();
    }

    public List<String> getDescriptionToCloseADealStringIds() {
        return this.descriptionToCloseADeal.getStringIds();
    }

    public Integer getSumOfDealConfigurationDataHashCode() {
        return this.sumOfDealConfigurationData.hashCode();
    }

    public Integer getStopLossConfigurationDataHashCode() {
        return this.stopLossConfigurationData.hashCode();
    }

    public Integer getTrailingStopConfigurationDataHashCode() {
        return this.trailingStopConfigurationData.hashCode();
    }

    public Integer getTakeProfitConfigurationDataHashCode() {
        return this.takeProfitConfigurationData.hashCode();
    }
}
