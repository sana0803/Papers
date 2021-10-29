package com.diary.api.controller;

import com.diary.api.request.UserSignupReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.common.auth.PapersUserDetails;
import com.diary.api.db.entity.User;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test/{id}")
    public String jwtTokenTest(@PathVariable String id){
        System.out.println(id);
        System.out.println(JwtTokenUtil.getToken(id));
        return JwtTokenUtil.getToken(id);
    }

    @GetMapping("/me")
    public User login(@ApiIgnore Authentication authentication) {
//        System.out.println(userReq.getUserId());
//        System.out.println(userReq.getPassword());

        PapersUserDetails userDetails = (PapersUserDetails)authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getUserByUserId(userId);
        return user;
    }

    @PostMapping()
    public ResponseEntity signUp(
            @RequestParam String userId,
            @RequestParam String userPwd,
            @RequestParam String userName,
            @RequestParam String userNickname
    ) {
        if (userService.signupUser(new UserSignupReq(userId, userPwd, userName, userNickname)))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입 실패"));
    }

}
