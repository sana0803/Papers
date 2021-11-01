package com.diary.api.controller;

import com.diary.api.db.entity.Font;
import com.diary.api.request.UserSignupReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.StickerPackagesRes;
import com.diary.common.auth.PapersUserDetails;
import com.diary.api.db.entity.User;
import com.diary.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @PutMapping("/mileage")
    public ResponseEntity<? extends BaseResponseBody> updateMileage(@ApiIgnore Authentication authentication, @RequestParam int amount) {

        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        User user = userService.getUserByUserId(userDetails.getUsername());
        if (user == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "잘못된 토큰"));
        userService.updateMileage(user, user.getUserMileage() + amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stickers")
    public ResponseEntity<List<StickerPackagesRes>> getStickers(@ApiIgnore Authentication authentication) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        User user = userService.getUserByUserId(userDetails.getUsername());
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getStickers(user));
    }

    @GetMapping("/fonts")
    public ResponseEntity<List<Font>> getFonts(@ApiIgnore Authentication authentication) {
        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        User user = userService.getUserByUserId(userDetails.getUsername());
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getFonts(user));
    }
}
