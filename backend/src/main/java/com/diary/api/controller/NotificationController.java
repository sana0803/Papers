package com.diary.api.controller;

import com.diary.api.db.entity.User;
import com.diary.api.response.AlarmDataSet;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.NotificationRes;
import com.diary.api.service.NotificationServiceImpl;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import io.swagger.annotations.Api;
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
import java.util.List;
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
    public SseEmitter subscribe(@RequestParam String uuid, @RequestParam String userId) {
        SseEmitter emitter = new SseEmitter(50000l);
        notificationService.addEmitter(uuid, new AlarmDataSet(userId, emitter));
        System.out.println("알림 / 로그인 된 유저 아이디 : " + userId);
        emitter.onTimeout(() -> {
                emitter.complete();
                notificationService.removeEmitter(uuid);
                notificationService.removeUser(userId);
        });
        emitter.onCompletion(() -> {
            notificationService.removeEmitter(uuid);
            notificationService.removeUser(userId);
        });
        return emitter;
    }

    @GetMapping("/publish")
    public void publish(@RequestParam String message) {
        notificationService.publish(message);
    }

    @GetMapping()
    public ResponseEntity<List<NotificationRes>> getNotifications (@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(notificationService.getNotifications(user));
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity updateNotificationRead(@ApiIgnore Authentication authentication, @PathVariable long notificationId) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();

        if (notificationService.updateNotificationRead(user, notificationId)) {
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "요청이 성공적으로 이루어졌습니다."));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "요청이 정상적으로 처리되지 않았습니다. "));
    }
}