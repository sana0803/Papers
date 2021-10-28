package com.diary.api.controller;

import com.diary.common.auth.SsafyUserDetails;
import com.diary.api.db.entity.User;
import com.diary.api.service.UserService;
import com.diary.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/users")
public class UserContlloer {
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

        SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getUserByUserId(userId);
        return user;
    }
}
