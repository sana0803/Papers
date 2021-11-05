package com.diary.api.service;

import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.response.StreamDataSet;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.reflections.Reflections.log;

@Service
public class NotificationServiceImpl implements NotificationService{
    private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();

    @Override
    public void addEmitter(String uuid, SseEmitter emitter) {
        CLIENTS.put(uuid, emitter);
        log.info("connection id : " + uuid);
        log.info("map size : " + CLIENTS.size());
    }

    @Override
    public void removeEmitter(String uuid) {
        CLIENTS.remove(uuid);
    }

    @Override
    public void publish(String message) {
        log.info("알림 내용 : " + message);
        Set<String> deadIds = new HashSet<>();
        log.info("알림 발생 시점에서 map 개 수 : " + CLIENTS.size());
        CLIENTS.forEach((id, emitter) -> {
            try {
                emitter.send(message, MediaType.APPLICATION_JSON);
                log.info("알림 클라이언트로 보냄");
            } catch (Exception e) {
                deadIds.add(id);
                log.error("* 에러가 발생해서 삭제 목록에 추가함 *");
            }
        });

        deadIds.forEach(CLIENTS::remove);
    }


}
