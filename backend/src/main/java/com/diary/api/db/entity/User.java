package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    private String userId;
    private String userPwd;
    private String userName;
    private int userMileage;
    private String userNickname;
    private String userProfile;

    @PrePersist
    public void setUp(){
        this.userMileage = 50;
    }
}
