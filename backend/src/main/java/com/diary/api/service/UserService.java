package com.diary.api.service;

import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.Font;
import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.request.NotificationReq;
import com.diary.api.request.UserLoginReq;
import com.diary.api.request.UserSignupReq;
import com.diary.api.response.NotificationRes;
import com.diary.api.response.StickerPackagesRes;
import com.diary.api.response.UserRes;

import java.util.List;

public interface UserService {
    boolean signupUser(UserSignupReq userSignupReq);
    User getUserByUserId(String userId);
    UserRes authenticate(UserLoginReq userLoginReq);
    boolean updateMileage(User user, int amount);
    List<StickerPackagesRes> getStickers(User user);
    List<Font> getFonts(User user);
    List<DiaryCover> getDiaryCover(User user);
    List<NotificationRes> getNotifications(User user);
    boolean createNotification(User user, NotificationReq notificationReq);
    boolean readNotification(User user, Long id);
}
