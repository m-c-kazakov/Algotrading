package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TimeFrame {

    M1(1, 30),
    H1(2, 24);

    int order;
    int batchSize;
}
