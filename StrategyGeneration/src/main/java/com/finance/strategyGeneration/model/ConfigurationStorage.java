package com.finance.strategyGeneration.model;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
public class ConfigurationStorage<T> {
    Map<T, Object> configurationData;

    public Map<T, Object> getConfigurationData() {
        return new HashMap<>();
    }
}
