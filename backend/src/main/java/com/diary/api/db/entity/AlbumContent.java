package com.diary.api.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class AlbumContent extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "albumId", referencedColumnName = "id", nullable = false)
    private Album album;

    private String albumContentText;
    private int albumContentPage;
    private String albumContentUrl;
}
