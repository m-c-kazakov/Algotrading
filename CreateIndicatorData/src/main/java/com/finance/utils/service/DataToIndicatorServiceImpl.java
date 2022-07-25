package com.finance.utils.service;

import com.finance.utils.model.DataOfIndicator;
import com.finance.utils.repository.DataOfIndicatorRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataToIndicatorServiceImpl implements DataToIndicatorService {

    DataOfIndicatorRepository repository;

    @Override
    @Cacheable(cacheNames = "saveDataOfIndicator", key = "#dataOfIndicator.uniqueIdentifier")
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public DataOfIndicator save(@NonNull DataOfIndicator dataOfIndicator) {
        return repository.getDataOfIndicatorByUniqueIdentifier(dataOfIndicator.getUniqueIdentifier())
                .orElseGet(() -> repository.save(dataOfIndicator));
    }
}
