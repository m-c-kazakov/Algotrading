package com.finance.strategyGeneration.service.broker;

import java.util.List;

public interface DataSender {
    void dataHandler(StringValue value);

    void dataHandler(List<StringValue> value);
}
