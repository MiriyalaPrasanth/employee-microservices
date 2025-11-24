package com.example.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.notification.entity.KafkaEvent;
import com.example.notification.repository.KafkaEventRepo;

@Service
public class NotificationListeners {

    @Autowired
    private KafkaEventRepo repo;

    @KafkaListener(topics = "employee-events", groupId = "notification-service-group")
    public void listenEmployee(String msg) {
        KafkaEvent event = new KafkaEvent();
        event.setEventType("EMPLOYEE_ADDED");
        event.setMessage(msg);
        event.setProducerService("employee-service");
        repo.save(event);
        
        System.out.println("Employee added event saved");
    }

    @KafkaListener(topics = "payroll-events", groupId = "payroll-service-group")
    public void listenPayroll(String msg) {
        KafkaEvent event = new KafkaEvent();
        event.setEventType("PAYROLL_ONBOARDED");
        event.setMessage(msg);
        event.setProducerService("payroll-service");
        repo.save(event);

        System.out.println("Payroll onboarded event saved");
    }
}
