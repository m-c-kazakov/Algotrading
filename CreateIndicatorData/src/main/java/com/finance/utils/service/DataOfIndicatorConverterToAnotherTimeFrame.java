package com.finance.utils.service;

import com.finance.strategyDescriptionParameters.TimeFrame;

import java.util.List;

public interface DataOfIndicatorConverterToAnotherTimeFrame {

    List<Integer> convert(TimeFrame desiredTimeFrame, TimeFrame currentTimeFrame, String binaryString);
}
