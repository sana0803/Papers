package com.diary.api.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class StickerPackage extends BaseEntity{
    int stickerPackagePrice;
    String stickerPackageName;

    @JsonIgnore
    @OneToMany(mappedBy = "stickerPackage", cascade = CascadeType.ALL)
    Set<Sticker> stickerSet = new HashSet<>();
}
