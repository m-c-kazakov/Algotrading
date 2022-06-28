package com.finance.dataHolder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Candle {
    // TODO Удалить использование класса

    int closingPrice;
    int lowPrice;
    int highPrice;

}
