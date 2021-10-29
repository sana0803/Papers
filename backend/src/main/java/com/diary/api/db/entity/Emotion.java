package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Emotion extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "noteId", referencedColumnName = "id", nullable = false)
    Note note;

    @ManyToOne
    @JoinColumn(name = "writerId", referencedColumnName = "userId", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "emotionInfoId", referencedColumnName = "id", nullable = false)
    EmotionInfo emotionInfo;

}
