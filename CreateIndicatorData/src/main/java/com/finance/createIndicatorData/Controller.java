package com.finance.createIndicatorData;

public class Controller {

    DataOfStrategyGenerationService dataOfStrategyGenerationService;


    public void execute(RequestDataOfStrategy request, ResponseDataOfStrategy response) {

        request.getCandlesInformation();
    }
}
