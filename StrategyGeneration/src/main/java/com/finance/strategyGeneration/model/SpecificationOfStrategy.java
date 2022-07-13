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
import java.util.Date;
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
    Date dateOfCreation;
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

    public List<InformationOfIndicator> receiveDescriptionToCloseADealIndicators() {
        return this.descriptionToCloseADeal.getInformationOfIndicators();
    }

    public List<InformationOfIndicator> receiveDescriptionToOpenADealIndicators() {
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

    public List<String> receiveDescriptionToOpenADealStringIds() {
        return this.descriptionToOpenADeal.receiveStringIds();
    }

    public List<String> receiveDescriptionToCloseADealStringIds() {
        return this.descriptionToCloseADeal.receiveStringIds();
    }

    public Integer receiveSumOfDealConfigurationDataHashCode() {
        return this.sumOfDealConfigurationData.hashCode();
    }

    public Integer receiveStopLossConfigurationDataHashCode() {
        return this.stopLossConfigurationData.hashCode();
    }

    public Integer receiveTrailingStopConfigurationDataHashCode() {
        return this.trailingStopConfigurationData.hashCode();
    }

    public Integer receiveTakeProfitConfigurationDataHashCode() {
        return this.takeProfitConfigurationData.hashCode();
    }
}
