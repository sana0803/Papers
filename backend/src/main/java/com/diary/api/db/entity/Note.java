package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Note extends BaseEntity {

    String noteTitle;
    String noteContent;
    LocalDate noteCreateDate;
    LocalTime noteCreateTime;

    @ManyToOne
    @JoinColumn(name = "writerId", referencedColumnName = "userId", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "diaryId", referencedColumnName = "id", nullable = false)
    Diary diary;

    @ManyToOne
    @JoinColumn(name = "fontId", referencedColumnName = "id", nullable = false)
    Font font;

    @ManyToOne
    @JoinColumn(name = "layoutId", referencedColumnName = "id", nullable = false)
    NoteLayout noteLayout;

    @ManyToOne
    @JoinColumn(name = "designId", referencedColumnName = "id", nullable = false)
    NoteDesign noteDesign;

    @PrePersist
    public void setUp() {
        this.noteCreateDate = LocalDate.now();
        this.noteCreateTime = LocalTime.now();
    }
}
