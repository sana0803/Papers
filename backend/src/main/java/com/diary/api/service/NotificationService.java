package com.diary.api.service;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    void addEmitter(String uuid, SseEmitter emitter);
    void removeEmitter(String uuid);
    void publish(String message);
}
