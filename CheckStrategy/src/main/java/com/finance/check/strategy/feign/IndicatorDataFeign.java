package com.finance.check.strategy.feign;

import com.finance.createIndicatorData.IndicatorDataApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient
public interface IndicatorDataFeign extends IndicatorDataApi {

}
