package com.diary.api.service;

import com.diary.api.db.entity.User;
import com.diary.api.request.UserReq;

public interface UserService {
    User createUser(UserReq userReq);
    User getUserByUserId(String userId);
}
