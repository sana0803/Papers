package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReq {
    String userId;
    String userPwd;
}
