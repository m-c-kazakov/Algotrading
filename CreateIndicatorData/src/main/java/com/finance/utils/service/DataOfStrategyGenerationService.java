package com.finance.utils.service;

import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.dto.ResponseDataOfStrategy;

public interface DataOfStrategyGenerationService {

    ResponseDataOfStrategy generate(RequestDataOfStrategy request);
}
