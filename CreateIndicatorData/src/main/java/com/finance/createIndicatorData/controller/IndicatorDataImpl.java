package com.finance.createIndicatorData.controller;

import com.finance.createIndicatorData.IndicatorDataApi;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;
import com.finance.createIndicatorData.service.DataOfStrategyGenerationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorDataImpl implements IndicatorDataApi {

    DataOfStrategyGenerationService dataOfStrategyGenerationService;

    @PostMapping("/generateDataOfIndicators")
    public ResponseDataOfStrategy generateDataOfIndicators(@RequestBody RequestDataOfStrategy request) {
        return dataOfStrategyGenerationService.generate(request);
    }
}
