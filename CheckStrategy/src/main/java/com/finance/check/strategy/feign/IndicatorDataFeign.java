package com.finance.check.strategy.feign;

import com.finance.createIndicatorData.IndicatorDataApi;
import com.finance.createIndicatorData.dto.RequestDataOfStrategy;
import com.finance.createIndicatorData.dto.ResponseDataOfStrategy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "IndicatorDataFeign")
public interface IndicatorDataFeign extends IndicatorDataApi {

    @Override
    @PostMapping("/createIndicatorData/generateDataOfIndicators")
    ResponseDataOfStrategy generateDataOfIndicators(@RequestBody RequestDataOfStrategy request);

}
