package com.finance.strategyGeneration.config.configurationProperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.kafka.consumer")
public class KafkaConsumerConfigurationProperties {

    String bootstrap_servers_config;
    String group_id_config;
    String group_instance_id_config;
    String enable_auto_commit_config;
    String auto_commit_interval_ms_config;
    String auto_offset_reset_config;
    Integer max_poll_records_config;
    Integer max_poll_interval_ms_config;
    String topic_name;
    Integer timeout_duration;
}
