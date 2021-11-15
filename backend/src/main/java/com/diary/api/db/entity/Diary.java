package com.diary.api.db.entity;


import com.diary.api.db.repository.DiaryCoverRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Diary extends BaseEntity {

    String diaryTitle;

    LocalDate diaryCreatedDate;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "userId", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "coverId", referencedColumnName = "id", nullable = false)
    DiaryCover diaryCover;

    @JsonIgnore
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private Set<UserDiary> userDiaries = new HashSet<>();

    @PrePersist
    public void setUp(){
        this.diaryCreatedDate = LocalDate.now();
    }
}
