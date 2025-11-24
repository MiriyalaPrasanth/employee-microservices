package com.example.payroll.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.payroll.producer.PayrollEventProducer;

@Service
public class EmployeeEventConsumer {

    private static final Logger logger = LogManager.getLogger(EmployeeEventConsumer.class);

    @Autowired
    private PayrollEventProducer payrollEventProducer;

    @KafkaListener(topics = "employee-events", groupId = "payroll-service-group")
    public void consume(String message) {
        logger.info("📥 [PayrollService] Received Employee Event: {}", message);

        if (message.contains("added")) {
            String payrollEvent = message.replace("Employee added", "Payroll initialized for");
            payrollEventProducer.sendPayrollEvent(payrollEvent);
        }
    }
}
