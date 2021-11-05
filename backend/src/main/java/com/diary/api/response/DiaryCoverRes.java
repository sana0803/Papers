package com.diary.api.response;

import com.diary.api.db.entity.DiaryCover;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryCoverRes {

    Long id;
    String CoverUrl;
    int CoverPrice;
    String CoverName;
    boolean isOwned;

    public DiaryCoverRes(DiaryCover diaryCover){
        this.id = diaryCover.getId();
        this.CoverName = diaryCover.getCoverName();
        this.CoverUrl = diaryCover.getCoverUrl();
        this.CoverPrice = diaryCover.getCoverPrice();
    }
}
