package com.finance.check.strategy.dealManagement.trailingStopManagement;

import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.stereotype.Component;

@Component("FIXED_TRAILING_STOP_TYPE")
public class FixedPipTrailingStop implements TrailingStopManager {
    @Override
    public void update(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor) {

        descriptionOfStrategy.getFromTrailingStopConfigurationData(TrailingStopConfigurationKey.FIXED_TRAILING_STOP)
                .map(Integer::parseInt)
                .ifPresent(fixedValue -> {
                    // TODO При написании теста отрефакторить. Слишком умного уровней вложенных if
                    if (descriptionOfStrategy.getTypeOfDeal() == TypeOfDeal.BUY) {
                        int newTrailingStop = descriptionOfStrategy.getClosingPrice(cursor) - fixedValue;
                        if (dataOfDeal.getTrailingStop() < newTrailingStop) {
                            dataOfDeal.setTrailingStop(newTrailingStop);
                        }
                    } else if (descriptionOfStrategy.getTypeOfDeal() == TypeOfDeal.SELL) {
                        int newTrailingStop = descriptionOfStrategy.getClosingPrice(cursor) + fixedValue;
                        if (dataOfDeal.getTrailingStop() > newTrailingStop) {
                            dataOfDeal.setTrailingStop(newTrailingStop);
                        }
                    } else {
                        throw new RemoteTimeoutException(
                                "Передано неизвестное значение TypeOfDeal==" + descriptionOfStrategy.getTypeOfDeal());
                    }
                });
    }
}
