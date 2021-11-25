package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class NotificationInfo extends BaseEntity {
    private String notInfoName;
}
