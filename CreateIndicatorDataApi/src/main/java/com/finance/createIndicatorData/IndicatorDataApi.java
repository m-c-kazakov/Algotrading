package com.finance.createIndicatorData;

import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IndicatorDataApi {

    @PostMapping("/createIndicatorData/generateDataOfIndicators")
    ResponseDataOfStrategy generateDataOfIndicators(@RequestBody RequestDataOfStrategy request);
}
