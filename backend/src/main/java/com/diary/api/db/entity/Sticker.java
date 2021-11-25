package com.diary.api.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Sticker extends BaseEntity{
    String stickerUrl;

    @ManyToOne
    @JoinColumn(name = "stickerPackageId", referencedColumnName = "id", nullable = false)
    StickerPackage stickerPackage;

    @JsonIgnore
    @OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
    private Set<NoteSticker> noteStickers = new HashSet<>();
}
