package com.diary.api.response;

import com.diary.api.db.entity.Sticker;
import com.diary.api.db.entity.StickerPackage;
import com.diary.api.db.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StickerPackagesRes {
    private Long id;
    private String stickerPackageName;
    private int stickerPackagePrice;
    private List<StickerRes> stickerList;
    private boolean isOwned;

    public StickerPackagesRes(StickerPackage stickerPackage, List<StickerRes> stickers) {
        this.id = stickerPackage.getId();
        this.stickerPackageName = stickerPackage.getStickerPackageName();
        this.stickerPackagePrice = stickerPackage.getStickerPackagePrice();
        this.stickerList = stickers;
    }
}
