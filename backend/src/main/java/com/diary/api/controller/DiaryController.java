package com.diary.api.controller;


import com.diary.api.request.DiaryReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.DiaryRes;
import com.diary.api.service.DiaryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@Api(value = "일기장 API", tags = {"Diary"})
@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @PostMapping()
    @ApiOperation(value = "일기장 만들기", notes = "일기장을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 등록 성공"),
            @ApiResponse(code = 401, message = "일기장 만들기 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<DiaryRes> createDiary(
            @RequestParam Long coverId,
            @RequestParam String diaryTitle,
            @RequestParam String diaryDesc,
            @RequestParam String owner
            ) throws IOException {
        return ResponseEntity.ok(diaryService.createDiary(DiaryReq.of(coverId, diaryTitle, diaryDesc, owner)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "일기장 삭제", notes = "일기장 id로 일기장 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 삭제 성공"),
            @ApiResponse(code = 401, message = "일기장 삭제 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteDiary(@PathVariable Long id, @ApiIgnore Authentication authentication) {
        diaryService.deleteDiary(id);
        return ResponseEntity.status(204).body(BaseResponseBody.of(200, "Delete Success"));
    }
}
