package com.diary.api.response;

import com.diary.api.db.entity.Font;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FontRes {

    Long id;
    String fontName;
    int fontPrice;
    boolean isOwned;

    public FontRes(Font font){
        this.id = font.getId();
        this.fontName = font.getFontName();
        this.fontPrice = getFontPrice();
    }
}
