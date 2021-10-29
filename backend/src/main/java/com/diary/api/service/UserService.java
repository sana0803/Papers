package com.diary.api.service;

import com.diary.api.db.entity.User;
import com.diary.api.request.UserLoginReq;
import com.diary.api.request.UserSignupReq;
import com.diary.api.response.UserRes;

public interface UserService {
    boolean signupUser(UserSignupReq userSignupReq);
    User getUserByUserId(String userId);
    UserRes authenticate(UserLoginReq userLoginReq);
}
