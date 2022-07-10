package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.mapper.StrategyInformationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationOfStrategyServiceImpl implements SpecificationOfStrategyService {

    SpecificationOfStrategyRepository repository;
    StrategyInformationMapper mapper;
    @NonFinal
    @Autowired
    SpecificationOfStrategyService self;

    @Override
    @Transactional
    public void save(SpecificationOfStrategy specificationOfStrategy) {
        repository.save(specificationOfStrategy);

    }

    @Override
    public void saveAll(List<DescriptionOfStrategy> populationAfterSelection) {
        log.info("START SAVE ALL");

        Map<Integer, SpecificationOfStrategy> duplicateMap = new HashMap<>();
        populationAfterSelection
                .stream()
                .map(mapper::mapTo)
                .map(specificationOfStrategy -> specificationOfStrategy.withHashCode(specificationOfStrategy.hashCode()))
                .distinct()
                .forEach(specificationOfStrategy -> {
                    try {
                        // TODO убрать вызов self
                        self.save(specificationOfStrategy);
                    } catch (Exception exception) {
                        log.error(
                                "ОШИБКА={}, specificationOfStrategy.hashCode()={}; specificationOfStrategy.getHashCode()={}",
                                exception.getMessage(), specificationOfStrategy.hashCode(), specificationOfStrategy.getHashCode());
                        duplicateMap.put(specificationOfStrategy.hashCode(), specificationOfStrategy);
                    }
                });

        System.out.println("END");
        for (Map.Entry<Integer, SpecificationOfStrategy> node : duplicateMap.entrySet()) {
            Optional<SpecificationOfStrategy> byHashCode = repository.findByHashCode(
                    node.getKey());
            if (byHashCode.isPresent()) {
                log.warn("Найден дубликат по hashCode={}; Duplicate={}; Original={}", node.getKey().toString(), node.getValue().toString(),
                        byHashCode.get().toString());
            } else {
                log.error("Сущность hashCode={} не найдена", node.getKey());
                repository.save(node.getValue());
            }
        }


//        repository.saveAll(specificationOfStrategies);
    }

}
