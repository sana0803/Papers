package com.diary.api.service;


import com.diary.api.db.entity.*;
import com.diary.api.db.repository.DiaryRepository;
import com.diary.api.db.repository.DiaryRepositorySupport;
import com.diary.api.db.repository.NoteRepository;
import com.diary.api.db.repository.UserDiaryRepository;
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

    @Autowired
    UserDiaryRepository userDiaryRepository;

    // 일기장 생성
    @Override
    public DiaryRes createDiary(DiaryReq diaryReq) {
        User ownerId = userService.getUserByUserId(diaryReq.getOwnerId());
//        User ownerId = userService.getUserByUserId(user.getUserId());
        DiaryCover coverId = diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get();
        String diaryTitle = diaryReq.getDiaryTitle();
        String diaryDesc = diaryReq.getDiaryDesc();

        Diary diary = new Diary();
        diary.setDiaryCover(coverId);
        diary.setDiaryTitle(diaryTitle);
        diary.setDiaryDesc(diaryDesc);
        diary.setUser(ownerId);
        diary.setDiaryCreatedDate(LocalDate.now());

        diaryRepository.save(diary);

        UserDiary userDiary = new UserDiary();
        userDiary.setDiary(diaryRepository.getOne(diary.getId()));
        userDiary.setUser(userService.getUserByUserId(diaryReq.getOwnerId()));
//        userDiary.setUser(userService.getUserByUserId(user.getUserId()));
        userDiaryRepository.save(userDiary);

        return DiaryRes.of(diary);
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
    public List<DiaryRes> getDiaryList(String userId) {
        List<DiaryRes> diaryResList = null;
        User ownerId = userService.getUserByUserId(userId);
        List<Diary> diaryList = diaryRepository.findAllByUser(ownerId);

        if (diaryList.size() != 0) {
            diaryResList = convertToDiaryRes(diaryList);
        }
        return diaryResList;
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

    // 일기장을 필요정보만 리턴
    public List<DiaryRes> convertToDiaryRes(List<Diary> diaries) {
        List<DiaryRes> diaryResList = new ArrayList<>();
        for (Diary diary : diaries) {
            diaryResList.add(new DiaryRes(
                    diary.getId(),
                    diary.getDiaryCover().getId(),
                    diary.getDiaryTitle(),
                    diary.getDiaryDesc(),
                    diary.getUser().getUserId(),
                    diary.getDiaryCreatedDate()
            ));
        }
        return diaryResList;
    }
}
