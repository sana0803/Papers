package com.diary.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmotionStatusRes {
    int likeEmotionCount;
    int funnyEmotionCount;
    int sadEmotionCount;
    boolean isPressLike;
    boolean isPressFunny;
    boolean isPressSad;

    public static EmotionStatusRes of (Map<String, Integer> emotionMap, boolean isPressLike, boolean isPressFunny, boolean isPressSad) {
        EmotionStatusRes e = new EmotionStatusRes();
        e.likeEmotionCount = emotionMap.get("좋아요");
        e.funnyEmotionCount = emotionMap.get("웃겨요");
        e.sadEmotionCount = emotionMap.get("슬퍼요");
        e.setPressLike(isPressLike);
        e.setPressFunny(isPressFunny);
        e.setPressSad(isPressSad);
        return e;
    }

}
