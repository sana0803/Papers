package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class NoteMedia extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "noteId", referencedColumnName = "id", nullable = false)
    Note note;

    String mediaUrl;
    String mediaExtension;
}
