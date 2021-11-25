package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class NoteDesign extends BaseEntity{
    String designUrl;
}
