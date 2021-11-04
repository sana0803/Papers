package com.diary.api.controller;

import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.request.UserLoginReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.StreamDataSet;
import com.diary.api.response.UserRes;
import com.diary.api.service.NotificationService;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import springfox.documentation.annotations.ApiIgnore;

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
    NotificationService notificationService;

    private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();

//    @GetMapping(value = "/push", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public ResponseEntity<SseEmitter> fetchNotify(@RequestParam(required = false) String uuid) {
////        User user = JwtTokenUtil.getUser(authentication, userService);
////        if (user == null)
////            return ResponseEntity.noContent().build();
//
//        User user = userService.getUserByUserId("test1");
//        SseEmitter emitter = new SseEmitter();
//        StreamDataSet DATA_SET = new StreamDataSet(user, emitter);
//        final String UNIQUE_UUID = uuid;
//
//        System.out.println("controller : " + uuid);
//
//        // try - catch
////        notificationService.addEmitter(UNIQUE_UUID, DATA_SET);
//////        notificationService.fetch();
////
////        emitter.onCompletion(() -> { // 비동기 호출이 완료되면
////            System.out.println("컴플리트 호출됨");
////            notificationService.removeEmitter(UNIQUE_UUID);
////        });
////
//        emitter.onTimeout(() -> {
//            System.out.println("에미터 타임 아웃 발생");
//            emitter.complete();
//            notificationService.removeEmitter(UNIQUE_UUID);
//        });
//
//        System.out.println(uuid);
////        return null;
//        return new ResponseEntity<>(emitter, HttpStatus.OK);
//    }

    @GetMapping("/subscribe")
    public SseEmitter subscribe(@RequestParam String id) {

        SseEmitter emitter = new SseEmitter(50000l);
        CLIENTS.put(id, emitter);

        log.info("connection id : " + id);
        log.info("map size : " + CLIENTS.size());

        emitter.onTimeout(() -> {
                emitter.complete();
                CLIENTS.remove(id);
        });
        emitter.onCompletion(() -> {
                CLIENTS.remove(id);
        });
        return emitter;
    }

    @GetMapping("/publish")
    public void publish(@RequestParam String message) {
        log.info("알림 내용 : " + message);
        Set<String> deadIds = new HashSet<>();
        log.info("알림 발생 시점에서 map 개 수 : " + CLIENTS.size());
        CLIENTS.forEach((id, emitter) -> {
            try {
                emitter.send(message, MediaType.APPLICATION_JSON);
                log.info("알림 클라이언트로 보냄");
            } catch (Exception e) {
                deadIds.add(id);
                log.info("에러가 발생해서 삭제 목록에 추가함");
            }
        });

        deadIds.forEach(CLIENTS::remove);
    }
}