package com.example.hazelcastSample;

import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HazelcastConfig {

    @Value("${ticket.queue.name}")
    private String ticketQueueName;

    @Bean
    @Primary
    public HazelcastInstance hazelcast() {
        Config config = new Config();

        // Configure the priority queue
        QueueConfig queueConfig = config.getQueueConfig("default");
        queueConfig.setName(ticketQueueName)
                .setPriorityComparatorClassName(TicketPriorityComparator.class.getName());

        config.addQueueConfig(queueConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}