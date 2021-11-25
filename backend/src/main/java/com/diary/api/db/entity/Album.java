package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Album extends BaseEntity implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    private String albumName;
    private LocalDate albumDate;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    Set<AlbumContent> albumContents = new HashSet<>();

    @PrePersist
    public void setUp () {
        this.albumDate = LocalDate.now();
    }
}
