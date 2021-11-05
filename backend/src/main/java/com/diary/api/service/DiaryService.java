package com.diary.api.service;

import com.diary.api.db.entity.User;
import com.diary.api.request.DiaryInviteReq;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;
import com.diary.api.response.NoteRes;

import java.util.List;

public interface DiaryService {
    // 일기장 생성
    DiaryRes createDiary(User user, DiaryReq diaryReq);
    // 일기장 수정
    DiaryRes updateDiary(Long id, DiaryReq diaryReq);
    // 내 일기장 전체 조회
    List<DiaryRes> getDiaryList(User user);
    // 일기장 한개 조회(일기 전체 조회)
    List<NoteRes> getDiary(Long id);
    // 일기장 삭제
    void deleteDiary(Long id);
    // 일기장 초대
    boolean inviteDiary(User user, DiaryInviteReq diaryInviteReq);
    // 일기장 초대된 사람 삭제
    boolean disinviteDiary(Long diaryId, String userId);
}
