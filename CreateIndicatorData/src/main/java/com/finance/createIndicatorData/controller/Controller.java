package com.finance.createIndicatorData.controller;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;
import com.finance.createIndicatorData.service.DataOfStrategyGenerationService;

public class Controller {

    DataOfStrategyGenerationService dataOfStrategyGenerationService;


    public ResponseDataOfStrategy execute(RequestDataOfStrategy request) {

        request.getCandlesInformation();
        return null;
    }
}
