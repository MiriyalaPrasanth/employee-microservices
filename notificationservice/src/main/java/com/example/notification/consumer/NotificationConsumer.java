package com.example.notification.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private static final Logger logger = LogManager.getLogger(NotificationConsumer.class);

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "employee-events", groupId = "notification-service-group")
    public void handleEmployeeEvent(String message) {
        logger.info("📨 [NotificationService] Received Employee Event: {}", message);

        if (message.contains("added")) {
            String email = extractEmailFromMessage(message);
			/*
			 * sendEmail(email, "Welcome to the Organization!",
			 * "🎉 Congrats! You’ve been successfully onboarded. " + message);
			 */
        }
    }

    @KafkaListener(topics = "payroll-events", groupId = "notification-service-group")
    public void handlePayrollEvent(String message) {
        logger.info("💸 [NotificationService] Received Payroll Event: {}", message);
		/*
		 * sendEmail("hr@company.com", "Payroll Setup Complete",
		 * "Payroll process completed for employee: " + message);
		 */
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            mailSender.send(mail);
            logger.info("✅ Email sent successfully to {}", to);
        } catch (Exception e) {
            logger.error("❌ Failed to send email to {}: {}", to, e.getMessage());
        }
    }

    private String extractEmailFromMessage(String message) {
        // Mock logic: extract from text
        return "prasumiriyala@gmail.com";
    }
}
