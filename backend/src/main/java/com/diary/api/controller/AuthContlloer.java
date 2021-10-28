package com.diary.api.controller;

import com.diary.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthContlloer {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/{id}")
    public String jwtTokenTest(@PathVariable String id){
        System.out.println(id);
        System.out.println(JwtTokenUtil.getToken(id));
        return JwtTokenUtil.getToken(id);
    }
}
