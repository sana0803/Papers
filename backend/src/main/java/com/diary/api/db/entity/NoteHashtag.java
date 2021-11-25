package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class NoteHashtag extends BaseEntity {

    String tagValue;

    @ManyToOne
    @JoinColumn(name = "noteId", referencedColumnName = "id", nullable = false)
    Note note;

}
