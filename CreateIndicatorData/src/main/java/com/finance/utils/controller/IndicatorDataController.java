package com.finance.utils.controller;

import com.finance.utils.IndicatorDataApi;
import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.dto.ResponseDataOfStrategy;
import com.finance.utils.service.DataOfStrategyGenerationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorDataController implements IndicatorDataApi {

    DataOfStrategyGenerationService dataOfStrategyGenerationService;

    @PostMapping("/generateDataOfIndicators")
    @SneakyThrows
    public ResponseDataOfStrategy generateDataOfIndicators(@RequestBody RequestDataOfStrategy request) {
        log.info(">> generateDataOfIndicators={}", request);
        return dataOfStrategyGenerationService.generate(request);
    }
}
