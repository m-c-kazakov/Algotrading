package com.finance.strategyGeneration.model.storage;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@Value
@EqualsAndHashCode
public class ConfigurationStorage<T> {
    Map<T, String> configurationData;
}
