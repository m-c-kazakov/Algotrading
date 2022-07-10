package com.finance.strategyGeneration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.model.converter.*;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {

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

    @Override
    @Bean
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
                jsonToIndicatorParameterConfigurationStorageConverter()
        );
    }
}
