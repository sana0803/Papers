package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserDiary extends BaseEntity{

    private Long userDiaryId;

    private String guestId;

    @ManyToOne
    @JoinColumn(name = "diary_id", nullable = false)
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "userId" ,nullable = false)
    private User owner;

    @PrePersist
    public void setUp(){
        this.userDiaryId = super.id;
    }

}
