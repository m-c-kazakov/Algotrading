package com.finance.utils;

import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.dto.ResponseDataOfStrategy;

public interface IndicatorDataApi {

    ResponseDataOfStrategy generateDataOfIndicators(RequestDataOfStrategy request);
}
