package com.finance.strategyGeneration.strategyDescriptionParameters.indicators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("SMA")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SmaParameters {

    CALCULATE_BY("По какому значению рассчитывать индикатор? Цена закрытия, открытия, высшая, нижняя - TypeOfBar"),
    PERIOD("Количество свечей которое будет учитываться для расчета значения");

    String description;

}
