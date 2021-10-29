package com.diary.api.service;


import com.diary.api.db.entity.Diary;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;

public interface DiaryService {
    // 일기장 생성
    DiaryRes createDiary(DiaryReq diaryReq);

}
