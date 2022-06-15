package com.finance.check.strategy.dealManagement.trailingStopManagement;

import com.finance.check.strategy.dataHolder.DataOfDeal;
import com.finance.check.strategy.dataHolder.DataOfStrategy;
import com.finance.check.strategy.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.check.strategy.strategyDescriptionParameters.TypeOfDeal;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.stereotype.Component;

@Component("FIXED")
public class FixedPipTrailingStop implements TrailingStopManager {
    @Override
    public void update(DataOfStrategy dataOfStrategy, DataOfDeal dataOfDeal, int cursor) {
        dataOfStrategy.getFromTrailingStopConfigurationData(TrailingStopConfigurationKey.FIXED)
                .map(int.class::cast)
                .ifPresent(fixedValue -> {
                    // TODO При написании теста отрефакторить. Слишком умного уровней вложенных if
                    if (dataOfStrategy.getTypeOfDeal() == TypeOfDeal.BUY) {
                        int newTrailingStop = dataOfStrategy.getClosingPrice(cursor) - fixedValue;
                        if (dataOfDeal.getTrailingStop() < newTrailingStop) {
                            dataOfDeal.setTrailingStop(newTrailingStop);
                        }
                    } else if (dataOfStrategy.getTypeOfDeal() == TypeOfDeal.SELL) {
                        int newTrailingStop = dataOfStrategy.getClosingPrice(cursor) + fixedValue;
                        if (dataOfDeal.getTrailingStop() > newTrailingStop) {
                            dataOfDeal.setTrailingStop(newTrailingStop);
                        }
                    } else {
                        throw new RemoteTimeoutException(
                                "Передано неизвестное значение TypeOfDeal==" + dataOfStrategy.getTypeOfDeal());
                    }
                });
    }
}
