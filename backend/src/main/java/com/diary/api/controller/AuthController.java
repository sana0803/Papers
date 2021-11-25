package com.diary.api.controller;

import com.diary.api.request.UserLoginReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.UserRes;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<? extends BaseResponseBody> login(@RequestBody UserLoginReq userLoginReq){
        UserRes userRes = userService.authenticate(userLoginReq);
        if (userRes == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증실패"));
        userRes.setStatusCode(200);
        userRes.setMessage("로그인 되었습니다.");
        return ResponseEntity.ok(userRes);
    }
}
