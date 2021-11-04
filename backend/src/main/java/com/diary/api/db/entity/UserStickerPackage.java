package com.diary.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserStickerPackage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name ="userId", referencedColumnName = "userId", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name="stickerPackageId", referencedColumnName = "id", nullable = false)
    StickerPackage stickerPackage;
}
