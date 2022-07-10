package com.finance.strategyGeneration.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
@EqualsAndHashCode
public class ConfigurationStorage<T> {
    Map<T, Object> configurationData;

    public Map<T, Object> getConfigurationData() {
        return new HashMap<>();
    }
}
