package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class NoteSticker extends BaseEntity {

    String topPixel;
    String leftPixel;

    @ManyToOne
    @JoinColumn(name = "noteId", referencedColumnName = "id", nullable = false)
    Note note;

    @ManyToOne
    @JoinColumn(name = "stickerId", referencedColumnName = "id", nullable = false)
    Sticker sticker;

}
