package com.diary.api.service;


import com.diary.api.db.entity.Diary;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;
import com.diary.api.response.NoteRes;

import java.util.List;

public interface DiaryService {
    // 일기장 생성
    DiaryRes createDiary(DiaryReq diaryReq);
    // 일기장 수정
    DiaryRes updateDiary(Long id, DiaryReq diaryReq);
    // 일기장 전체 조회
    List<DiaryRes> getDiaryList(String ownerId);
    // 일기장 한개 조회
    List<NoteRes> getDiary(Long id);
    // 일기장 삭제
    void deleteDiary(Long id);
}
