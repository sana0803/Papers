package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Diary extends BaseEntity {

    private String diaryTitle;

    private String diaryDesc;

    private LocalDate diaryCreatedDate;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "userId", nullable = false)
    private User user;

    @PrePersist
    public void setUp(){
        this.diaryCreatedDate = LocalDate.now();
    }
}
