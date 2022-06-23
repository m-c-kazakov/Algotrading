package com.finance.createIndicatorData.service;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;

public interface DataOfStrategyGenerationService {

    ResponseDataOfStrategy generate(RequestDataOfStrategy request);
}
