package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.feign.IndicatorDataFeign;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.dto.ResponseDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataOfStrategyGeneratorServiceImpl implements DataOfStrategyGeneratorService {

    IndicatorDataFeign indicatorDataFeign;

    @Override
    public DescriptionOfStrategy generateDataOfIndicators(DescriptionOfStrategy descriptionOfStrategy) {
        RequestDataOfStrategy requestDataOfStrategy = createRequestDataOfStrategy(descriptionOfStrategy);

        ResponseDataOfStrategy responseDataOfStrategy = indicatorDataFeign.generateDataOfIndicators(
                requestDataOfStrategy);

        return updateDescriptionOfStrategy(descriptionOfStrategy, responseDataOfStrategy);
    }

    private DescriptionOfStrategy updateDescriptionOfStrategy(DescriptionOfStrategy descriptionOfStrategy, ResponseDataOfStrategy responseDataOfStrategy) {
        return descriptionOfStrategy
                .withDataOfCandles(responseDataOfStrategy.getCandles())
                .withDecisionToOpenADeal(responseDataOfStrategy.getDecisionToOpenADeal())
                .withDecisionToCloseADeal(responseDataOfStrategy.getDecisionToCloseADeal());
    }

    private RequestDataOfStrategy createRequestDataOfStrategy(DescriptionOfStrategy descriptionOfStrategy) {
        return RequestDataOfStrategy.builder()
                .typeOfDeal(descriptionOfStrategy.getTypeOfDeal())
                .candlesInformation(descriptionOfStrategy.getCandlesInformation())
                .descriptionToOpenADeal(descriptionOfStrategy.getDescriptionToOpenADeal())
                .descriptionToCloseADeal(descriptionOfStrategy.getDescriptionToCloseADeal())
                .build();
    }
}
