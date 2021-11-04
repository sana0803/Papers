package com.diary.api.controller;

import com.diary.api.service.NotificationServiceImpl;
import com.diary.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    UserService userService;

    @Autowired
    NotificationServiceImpl notificationService;



    @GetMapping("/subscribe")
    public SseEmitter subscribe(@RequestParam String uuid) {
        SseEmitter emitter = new SseEmitter(50000l);
        notificationService.addEmitter(uuid, emitter);

        emitter.onTimeout(() -> {
                emitter.complete();
                notificationService.removeEmitter(uuid);
        });
        emitter.onCompletion(() -> {
            notificationService.removeEmitter(uuid);
        });
        return emitter;
    }

    @GetMapping("/publish")
    public void publish(@RequestParam String message) {
        notificationService.publish(message);
    }
}