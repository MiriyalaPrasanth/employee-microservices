package com.example.payroll.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PayrollEventProducer {

    private static final Logger logger = LogManager.getLogger(PayrollEventProducer.class);
    private static final String TOPIC = "payroll-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendPayrollEvent(String event) {
        try {
            kafkaTemplate.send(TOPIC, event);
            logger.info("📤 [PayrollService] Sent Payroll Event: {}", event);
        } catch (Exception e) {
            logger.error("❌ Failed to send payroll event: {}", e.getMessage(), e);
        }
    }
}
