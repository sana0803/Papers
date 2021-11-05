package com.diary.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("DiaryInviteRequest")
public class DiaryInviteReq {
    @ApiModelProperty(name = "일기장 id", example = "1")
    Long diaryId;

    @ApiModelProperty(name = "초대 유저", example = "[test1, test2]" )
    List<String> inviteList;
}
