package com.diary.api.service;

import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.Font;
import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.request.NotificationReq;
import com.diary.api.request.UserLoginReq;
import com.diary.api.request.UserSignupReq;
import com.diary.api.request.UserUpdateReq;
import com.diary.api.response.NotificationRes;
import com.diary.api.response.StickerPackagesRes;
import com.diary.api.response.UserRes;
import com.diary.api.response.UserSearchRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean signupUser(UserSignupReq userSignupReq);
    boolean updateUser(User user, UserUpdateReq userUpdateReq) throws IOException;
    User getUserByUserId(String userId);
    UserRes authenticate(UserLoginReq userLoginReq);
    boolean updateMileage(User user, int amount);
    List<StickerPackagesRes> getStickers(User user);
    List<Font> getFonts(User user);
    List<DiaryCover> getDiaryCover(User user);
    List<NotificationRes> getNotifications(User user);
    boolean createNotification(User user, NotificationReq notificationReq);
    boolean readNotification(User user, Long id);
    List<UserSearchRes> getUsersLikeUserId(String userIdSubString);
    List<User> searchUserByUserID(String userId);
}
