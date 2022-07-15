package com.finance.check.strategy.feign;

import com.finance.utils.IndicatorDataApi;
import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.dto.ResponseDataOfStrategy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "IndicatorDataFeign", url = "http://localhost:8081/")
public interface IndicatorDataFeign extends IndicatorDataApi {

    @Override
    @PostMapping("/generateDataOfIndicators")
    ResponseDataOfStrategy generateDataOfIndicators(@RequestBody RequestDataOfStrategy request);

}
