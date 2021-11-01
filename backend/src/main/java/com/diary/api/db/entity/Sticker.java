package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Sticker extends BaseEntity{
    String stickerUrl;

    @ManyToOne
    @JoinColumn(name = "stickerPackageId", referencedColumnName = "id", nullable = false)
    StickerPackage stickerPackage;
}
