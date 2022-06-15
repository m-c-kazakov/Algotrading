package com.finance.strategyGeneration.random.indicator;

import com.finance.strategyGeneration.strategyDescriptionParameters.TypeOfBar;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.SmaParameters;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SmaRandomParametersGenerator implements RandomIndicatorParametersGenerator {

    List<SmaParameters> smaParameters = List.of(SmaParameters.values());
    List<TypeOfBar> typeOfBars = List.of(TypeOfBar.values());

    Map<SmaParameters, Supplier<String>> smaParametersSupplierMap = new HashMap<>();

    {
        smaParametersSupplierMap.put(SmaParameters.CALCULATE_BY,
                () -> String.valueOf(typeOfBars.get(ThreadLocalRandom.current()
                        .nextInt(typeOfBars.size()))));

        smaParametersSupplierMap.put(SmaParameters.PERIOD, () -> String.valueOf(ThreadLocalRandom.current()
                .nextInt(1, 101)));
    }


    @Override
    public Map<String, String> get() {

        Map<String, String> randomParameters = new HashMap<>();

        smaParameters.forEach(smaParameter -> {
            randomParameters.put(smaParameter.name(), smaParametersSupplierMap.get(smaParameter)
                    .get());
        });


        return randomParameters;
    }
}
