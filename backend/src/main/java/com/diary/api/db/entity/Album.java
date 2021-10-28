package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Album extends BaseEntity implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    private String albumName;
    private LocalDate albumDate;

    @PrePersist
    public void setUp () {
        this.albumDate = LocalDate.now();
    }
}
