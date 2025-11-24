package com.example.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification.entity.KafkaEvent;
import com.example.notification.repository.KafkaEventRepo;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private KafkaEventRepo repo;

    @GetMapping("/all")
    public List<KafkaEvent> getAllEvents() {
        return repo.findAll();
    }
}
