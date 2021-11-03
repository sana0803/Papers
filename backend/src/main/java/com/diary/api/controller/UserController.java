package com.diary.api.controller;

import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.Font;
import com.diary.api.db.entity.Notification;
import com.diary.api.request.NotificationReq;
import com.diary.api.request.UserSignupReq;
import com.diary.api.request.UserUpdateReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.NotificationRes;
import com.diary.api.response.StickerPackagesRes;
import com.diary.common.auth.PapersUserDetails;
import com.diary.api.db.entity.User;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity signup(@RequestBody UserSignupReq userSignupReq) {
        if (userService.getUserByUserId(userSignupReq.getUserId()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponseBody.of(409, "이미 가입된 회원"));
        if (userService.signupUser(userSignupReq))
            return ResponseEntity.ok().body(BaseResponseBody.of(200, "회원가입 성공"));
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입 실패"));
    }

    @PutMapping()
    public ResponseEntity updateUser(@ApiIgnore Authentication authentication, @ModelAttribute UserUpdateReq userUpdateReq) throws IOException {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "잘못된 토큰"));
        if (!userService.updateUser(user, userUpdateReq)) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "유저 정보 수정 중 문제 발생"));
        }
        return ResponseEntity.ok().body(BaseResponseBody.of(201, "회원 정보 수정정 성공"));

    }
    @PutMapping("/mileage")
    public ResponseEntity<? extends BaseResponseBody> updateMileage(@ApiIgnore Authentication authentication, @RequestParam int amount) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "잘못된 토큰"));
        userService.updateMileage(user, user.getUserMileage() + amount);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "마일리지 적립 성공"));
    }

    @GetMapping("/stickers")
    public ResponseEntity<List<StickerPackagesRes>> getStickers(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getStickers(user));
    }

    @GetMapping("/fonts")
    public ResponseEntity<List<Font>> getFonts(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getFonts(user));
    }

    @GetMapping("/covers")
    public ResponseEntity<List<DiaryCover>> getCovers(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getDiaryCover(user));
    }

    @GetMapping("/notification")
    public ResponseEntity<List<NotificationRes>> getNotifications(@ApiIgnore Authentication authentication) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getNotifications(user));
    }

    @PostMapping("/notification")
    public ResponseEntity createNotification(@ApiIgnore Authentication authentication, @RequestBody NotificationReq notificationReq) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        userService.createNotification(user, notificationReq);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/notification-read")
    public ResponseEntity readNotification(@ApiIgnore Authentication authentication, @RequestParam Long notId) {
        User user = JwtTokenUtil.getUser(authentication, userService);
        if (user == null) return ResponseEntity.notFound().build();
        userService.readNotification(user, notId);
        return ResponseEntity.ok().build();
    }
}
