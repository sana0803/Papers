package com.diary.api.db.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDiaryCover extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "coverId", referencedColumnName = "id", nullable = false)
    DiaryCover diaryCover;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    User user;

}
