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
import java.util.ArrayList;
import java.util.List;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    DiaryRepositorySupport diaryRepositorySupport;

    @Autowired
    UserService userService;

    // 일기장 생성
    @Override
    public DiaryRes createDiary(DiaryReq diaryReq) {
        User ownerId = userService.getUserByUserId(diaryReq.getOwnerId());
        DiaryCover coverId = diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get();
        String diaryTitle = diaryReq.getDiaryTitle();
        String diaryDesc = diaryReq.getDiaryDesc();

        Diary diary = new Diary();
        diary.setDiaryCover(coverId);
        diary.setDiaryTitle(diaryTitle);
        diary.setDiaryDesc(diaryDesc);
        diary.setUser(ownerId);
        diary.setDiaryCreatedDate(LocalDate.now());

        return DiaryRes.of(diaryRepository.save(diary));
    }

    // 내 일기장 전체 조회

    // 일기장 한개 조회

    // 일기장 삭제
    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }
}
