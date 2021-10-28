package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    String userId;
    String userPwd;
    String userName;
    int userMileage;
    String userNickname;
    String userProfile;

    @PrePersist
    public void setUp(){
        this.userMileage = 0;
    }
}
