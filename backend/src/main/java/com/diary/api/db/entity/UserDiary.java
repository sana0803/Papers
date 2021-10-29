package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserDiary extends BaseEntity{

    String guestId;

    @ManyToOne
    @JoinColumn(name = "diaryId", referencedColumnName = "id", nullable = false)
    Diary diary;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "userId", nullable = false)
    User user;

    @PrePersist
    public void setUp(){
    }

}
