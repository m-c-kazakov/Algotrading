package com.finance.check.strategy.dealManagement.updatingDealManagement;

import com.finance.check.strategy.dealManagement.trailingStopManagement.TrailingStopManager;
import com.finance.dataHolder.DataOfDeal;
import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Optional.ofNullable;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdatingDealManagerImpl implements UpdatingDealManager {

    Map<String, TrailingStopManager> trailingStopManagement;


    @Override
    public void update(DescriptionOfStrategy descriptionOfStrategy, DataOfDeal dataOfDeal, int cursor) {

        ofNullable(trailingStopManagement.get(descriptionOfStrategy.getTrailingStopType()
                .name())).ifPresent(
                trailingStopManager -> trailingStopManager.update(descriptionOfStrategy, dataOfDeal, cursor));

    }
}
