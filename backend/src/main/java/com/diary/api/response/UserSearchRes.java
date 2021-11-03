package com.diary.api.response;

import com.diary.api.db.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRes {
    private String userId;
    private String userNickname;
    private String userProfile;

    public UserSearchRes(User user) {
        this.userId = user.getUserId();
        this.userNickname = user.getUserNickname();
        this.userProfile = user.getUserProfile();
    }
}
