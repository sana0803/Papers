package com.diary.api.service;


import com.diary.api.db.entity.Diary;
import com.diary.api.db.entity.DiaryCover;
import com.diary.api.db.entity.Note;
import com.diary.api.db.entity.User;
import com.diary.api.db.repository.DiaryRepository;
import com.diary.api.db.repository.DiaryRepositorySupport;
import com.diary.api.db.repository.NoteRepository;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;
import com.diary.api.response.NoteRes;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    DiaryRepositorySupport diaryRepositorySupport;

    @Autowired
    UserService userService;

    @Autowired
    NoteRepository noteRepository;

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

    //일기장 수정
    @Override
    public DiaryRes updateDiary(Long id, DiaryReq diaryReq) {
        Diary diary = diaryRepository.getOne(id);
        diary.setDiaryCover(diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get());
        diary.setDiaryTitle(diaryReq.getDiaryTitle());
        diary.setDiaryDesc(diaryReq.getDiaryDesc());
        diary.setDiaryCreatedDate(LocalDate.now());
        return  DiaryRes.of(diaryRepository.save(diary));
    }

    // 내 일기장 전체 조회
    @Override
    public List<Diary> getDiaryList(String ownerId) {
        System.out.println(diaryRepository.findAllByUser(ownerId));
        return diaryRepository.findAllByUser(ownerId);
    }
    // 일기장 한개 조회
    @Override
    public List<NoteRes> getDiary(Long id) {
        return null;
    }

    // 일기장 삭제
    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }
}
