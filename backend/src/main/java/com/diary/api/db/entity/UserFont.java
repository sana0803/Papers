package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class UserFont extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "fontId", referencedColumnName = "id", nullable = false)
    private Font font;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;
}
