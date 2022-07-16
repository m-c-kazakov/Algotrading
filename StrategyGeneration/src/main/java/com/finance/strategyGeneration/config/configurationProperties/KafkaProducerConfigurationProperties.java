package com.finance.strategyGeneration.config.configurationProperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.kafka.producer")
public class KafkaProducerConfigurationProperties {
    @NotBlank
    String client_id_config;
    @NotBlank
    String bootstrap_servers_config;
    @NotBlank
    String acks_config;
    @NotBlank
    String retries_config;
    @NotBlank
    Integer batch_size_config;
    @NotBlank
    Integer linger_ms_config;
    @NotBlank
    Integer buffer_memory_config;
    @NotBlank
    Integer max_block_ms_config;
    @NotBlank
    String topic_name;
}
