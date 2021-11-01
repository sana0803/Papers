package com.diary.api.response;

import com.diary.api.db.entity.Sticker;
import com.diary.api.db.entity.StickerPackage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StickerRes {
    private Long id;
    private String stickerUrl;

    public StickerRes(Long id, String stickerUrl) {
        this.id = id;
        this.stickerUrl = stickerUrl;
    }
}
