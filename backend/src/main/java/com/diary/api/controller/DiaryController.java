package com.diary.api.controller;


import com.diary.api.db.entity.Diary;
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
import java.util.List;

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

    @PutMapping("/{id}")
    @ApiOperation(value = "일기장 수정", notes = "일기장 id로 일기장 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 수정 성공"),
            @ApiResponse(code = 401, message = "일기장 수정 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<DiaryRes> updateDiary
            (@PathVariable Long id,
             @RequestParam Long coverId,
             @RequestParam String diaryTitle,
             @RequestParam String diaryDesc,
             @RequestParam String owner
            ) throws IOException {
        return ResponseEntity.ok(diaryService.updateDiary(id, DiaryReq.of(coverId, diaryTitle, diaryDesc, owner)));
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
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Delete Success"));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "내 일기장 전체 조회", notes = "유저 id로 일기장 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기장 조회 성공"),
            @ApiResponse(code = 401, message = "일기장 조회 실패"),
            @ApiResponse(code = 500, message = "인증 오류")
    })
    public ResponseEntity<List<DiaryRes>> getUserDiary(@PathVariable String userId) {

        return ResponseEntity.status(200).body(diaryService.getDiaryList(userId));
    }
}
