package com.diary.api.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {
    @Id
    private String userId;
    private String userPwd;
    private int userMileage;
    private String userNickname;
    private String userProfile;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<EmotionLog> emotionLogs = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Note> notes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Diary> diaries = new HashSet<>();

    @PrePersist
    public void setUp(){
        this.userMileage = 50;
    }
}
