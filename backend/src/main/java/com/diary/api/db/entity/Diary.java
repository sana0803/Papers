package com.diary.api.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Diary")
public class Diary {

    @Id
    @Column(name = "diary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(name = "diary_title")
    private String diaryTitle;

    @Column(name = "diary_desc")
    private String diaryDesc;

    @Column(name = "diary_created_date")
    private LocalDate diaryCreatedDate;

}
