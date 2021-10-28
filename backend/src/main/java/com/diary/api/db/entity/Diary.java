package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Diary extends BaseEntity{

    private Long diaryId;

    private String diaryTitle;

    private String diaryDesc;

    private LocalDate diaryCreatedDate;

    @PrePersist
    public void setUp(){
        this.diaryId = super.id;
        this.diaryCreatedDate = LocalDate.now();
    }
}
