package com.diary.api.service;


import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.User;
import com.diary.api.db.repository.DiaryRepository;
import com.diary.api.db.repository.DiaryRepositorySupport;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserService userService;

    // 일기장 생성
    @Override
    public DiaryRes createDiary(DiaryReq diaryReq) {
        User ownerId = userService.getUserByUserId(diaryReq.getOwnerId());
        String diaryTitle = diaryReq.getDiaryTitle();
        String diaryDesc = diaryReq.getDiaryDesc();

        Diary diary = new Diary();
        diary.setDiaryTitle(diaryTitle);
        diary.setDiaryDesc(diaryDesc);
        diary.setUser(ownerId);
        diary.setDiaryCreatedDate(LocalDate.now());

        return DiaryRes.of(diaryRepository.save(diary));
    }
}
