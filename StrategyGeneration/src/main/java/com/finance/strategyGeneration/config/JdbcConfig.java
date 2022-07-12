package com.finance.strategyGeneration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.model.converter.indicatorParametersConfigurationStorage.IndicatorParametersConfigurationStorageToJsonConverter;
import com.finance.strategyGeneration.model.converter.indicatorParametersConfigurationStorage.JsonToIndicatorParameterConfigurationStorageConverter;
import com.finance.strategyGeneration.model.converter.indicatorsDescriptionStorageConverter.IdsToIndicatorsDescriptionStorageConverter;
import com.finance.strategyGeneration.model.converter.indicatorsDescriptionStorageConverter.IndicatorsDescriptionStorageToIdsConverter;
import com.finance.strategyGeneration.model.converter.informationOfCandlesStorageConverter.IdToInformationOfCandlesStorageConverter;
import com.finance.strategyGeneration.model.converter.informationOfCandlesStorageConverter.InformationOfCandlesStorageToIdConverter;
import com.finance.strategyGeneration.model.converter.stopLoss.StopLossConfigurationStorageToJsonConverter;
import com.finance.strategyGeneration.model.converter.stopLoss.StopLossJsonToConfigurationStorageConverter;
import com.finance.strategyGeneration.model.converter.sumOfDeal.SumOfDealConfigurationStorageToJsonConverter;
import com.finance.strategyGeneration.model.converter.sumOfDeal.SumOfDealJsonToConfigurationStorageConverter;
import com.finance.strategyGeneration.model.converter.takeProfit.TakeProfitConfigurationStorageToJsonConverter;
import com.finance.strategyGeneration.model.converter.takeProfit.TakeProfitJsonToConfigurationStorageConverter;
import com.finance.strategyGeneration.model.converter.trailingStop.TrailingStopConfigurationStorageToJsonConverter;
import com.finance.strategyGeneration.model.converter.trailingStop.TrailingStopJsonToConfigurationStorageConverter;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public StopLossConfigurationStorageToJsonConverter stopLossConfigurationStorageToJsonConverter() {
        return new StopLossConfigurationStorageToJsonConverter(objectMapper());
    }

    @Bean
    public StopLossJsonToConfigurationStorageConverter stopLossJsonToConfigurationStorageConverter() {
        return new StopLossJsonToConfigurationStorageConverter(objectMapper());
    }

    @Bean
    public SumOfDealConfigurationStorageToJsonConverter sumOfDealConfigurationStorageToJsonConverter() {
        return new SumOfDealConfigurationStorageToJsonConverter(objectMapper());
    }

    @Bean
    public SumOfDealJsonToConfigurationStorageConverter sumOfDealJsonToConfigurationStorageConverter() {
        return new SumOfDealJsonToConfigurationStorageConverter(objectMapper());
    }

    @Bean
    public TakeProfitConfigurationStorageToJsonConverter takeProfitConfigurationStorageToJsonConverter() {
        return new TakeProfitConfigurationStorageToJsonConverter(objectMapper());
    }

    @Bean
    public TakeProfitJsonToConfigurationStorageConverter takeProfitJsonToConfigurationStorageConverter() {
        return new TakeProfitJsonToConfigurationStorageConverter(objectMapper());
    }

    @Bean
    public TrailingStopConfigurationStorageToJsonConverter trailingStopConfigurationStorageToJsonConverter() {
        return new TrailingStopConfigurationStorageToJsonConverter(objectMapper());
    }

    @Bean
    public TrailingStopJsonToConfigurationStorageConverter trailingStopJsonToConfigurationStorageConverter() {
        return new TrailingStopJsonToConfigurationStorageConverter(objectMapper());
    }

    @Bean
    public IndicatorParametersConfigurationStorageToJsonConverter indicatorParametersConfigurationStorageToJsonConverter() {
        return new IndicatorParametersConfigurationStorageToJsonConverter(objectMapper());
    }

    @Bean
    public JsonToIndicatorParameterConfigurationStorageConverter jsonToIndicatorParameterConfigurationStorageConverter() {
        return new JsonToIndicatorParameterConfigurationStorageConverter(objectMapper());
    }

    @Bean
    public IndicatorsDescriptionStorageToIdsConverter indicatorsDescriptionStorageToIdsConverter() {
        return new IndicatorsDescriptionStorageToIdsConverter();
    }

    @Bean
    public IdsToIndicatorsDescriptionStorageConverter indicatorsDescriptionStorageConverter() {
        return new IdsToIndicatorsDescriptionStorageConverter();
    }

    @Bean
    public IdToInformationOfCandlesStorageConverter idToInformationOfCandlesStorageConverter() {
        return new IdToInformationOfCandlesStorageConverter();
    }

    @Bean
    public InformationOfCandlesStorageToIdConverter informationOfCandlesStorageToIdConverter() {
        return new InformationOfCandlesStorageToIdConverter();
    }

    @Override
    protected @NonNull List<?> userConverters() {
        return Arrays.asList(
                stopLossConfigurationStorageToJsonConverter(),
                stopLossJsonToConfigurationStorageConverter(),
                sumOfDealConfigurationStorageToJsonConverter(),
                sumOfDealJsonToConfigurationStorageConverter(),
                takeProfitConfigurationStorageToJsonConverter(),
                takeProfitJsonToConfigurationStorageConverter(),
                trailingStopConfigurationStorageToJsonConverter(),
                trailingStopJsonToConfigurationStorageConverter(),
                indicatorParametersConfigurationStorageToJsonConverter(),
                jsonToIndicatorParameterConfigurationStorageConverter(),
                indicatorsDescriptionStorageToIdsConverter(),
                indicatorsDescriptionStorageConverter(),
                idToInformationOfCandlesStorageConverter(),
                informationOfCandlesStorageToIdConverter()
        );
    }
}
