package com.diary.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DiaryRegisterRequest")
public class DiaryReq {
    @ApiModelProperty(name = "일기장 커버", example = "1")
    Long coverId;

    @ApiModelProperty(name = "일기장 제목", example = "싸피 일기장")
    String diaryTitle;

    @ApiModelProperty(name = "일기장 설명", example = "일상 기록")
    String diaryDesc;

//    @ApiModelProperty(name = "유저 id", example = "test1")
//    String ownerId;

//    public static DiaryReq of(Long coverId, String diaryTitle, String diaryDesc, String ownerId) {
//        DiaryReq diaryReq = new DiaryReq();
//
//        diaryReq.setCoverId(coverId);
//        diaryReq.setDiaryTitle(diaryTitle);
//        diaryReq.setDiaryDesc(diaryDesc);
//        diaryReq.setOwnerId(ownerId);
//        return diaryReq;
//    }
}
