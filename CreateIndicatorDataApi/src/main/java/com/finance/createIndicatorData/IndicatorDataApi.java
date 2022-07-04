package com.finance.createIndicatorData;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;

public interface IndicatorDataApi {

    ResponseDataOfStrategy generateDataOfIndicators(RequestDataOfStrategy request);
}
