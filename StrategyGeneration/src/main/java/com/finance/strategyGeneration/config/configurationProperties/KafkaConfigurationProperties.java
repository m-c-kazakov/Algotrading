package com.finance.strategyGeneration.config.configurationProperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.kafka")
public class KafkaConfigurationProperties {

    String client_id_config;
    String bootstrap_servers_config;
    String acks_config;
    String retries_config;
    Integer batch_size_config;
    Integer linger_ms_config;
    Integer buffer_memory_config;
    Integer max_block_ms_config;
}
