package com.diary.api.response;

import com.diary.api.db.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRes extends BaseResponseBody{
    private String userId;
    private String userNickname;
    private String userName;
    private int userMileage;
    private String userProfile;
    private String userToken;

    public UserRes(User user, String userToken) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userNickname = user.getUserNickname();
        this.userMileage = user.getUserMileage();
        this.userProfile = user.getUserProfile();
        this.userToken = userToken;
    }
}
