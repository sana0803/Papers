package com.diary.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteStickerReq {
    Long stickerId;
    String topPixel;
    String leftPixel;
}
