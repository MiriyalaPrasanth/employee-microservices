package com.example.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notification.entity.KafkaEvent;

@Repository
public interface KafkaEventRepo extends JpaRepository<KafkaEvent, Long> {
}
