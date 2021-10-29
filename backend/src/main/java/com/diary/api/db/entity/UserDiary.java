package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserDiary extends BaseEntity{

<<<<<<< HEAD
    String guestId;
=======
    private String guestId;
>>>>>>> 6fbbbaaa2d746034028d4f110c5b0193cd4c0d8e

    @ManyToOne
    @JoinColumn(name = "diaryId", referencedColumnName = "id", nullable = false)
    Diary diary;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "userId", nullable = false)
    User user;

}
