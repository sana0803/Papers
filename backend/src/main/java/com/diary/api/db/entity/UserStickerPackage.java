package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class UserStickerPackage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name ="userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="stickerPackageId", referencedColumnName = "id", nullable = false)
    private StickerPackage stickerPackage;


}
