package com.diary.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateReq {
    private MultipartFile userProfile;
//    private String userName;
    private String userPwd;
    private String userNickname;
}
