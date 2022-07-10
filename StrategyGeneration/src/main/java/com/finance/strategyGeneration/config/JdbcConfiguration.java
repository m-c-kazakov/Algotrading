package com.finance.strategyGeneration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.strategyGeneration.model.converter.*;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {




    @Override
//    @Bean
    protected @NonNull List<?> userConverters() {
        // TODO Попробовать инджектить бинами
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(
                new StopLossConfigurationStorageToJsonConverter(objectMapper),
                new StopLossJsonToConfigurationStorageConverter(objectMapper),
                new SumOfDealConfigurationStorageToJsonConverter(objectMapper),
                new SumOfDealJsonToConfigurationStorageConverter(objectMapper),
                new TakeProfitConfigurationStorageToJsonConverter(objectMapper),
                new TakeProfitJsonToConfigurationStorageConverter(objectMapper),
                new TrailingStopConfigurationStorageToJsonConverter(objectMapper),
                new TrailingStopJsonToConfigurationStorageConverter(objectMapper),
                new IndicatorParametersConfigurationStorageToJsonConverter(objectMapper),
                new JsonToIndicatorParameterConfigurationStorageConverter(objectMapper)
        );
    }
}
