package com.diary.api.service;

import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.request.DiaryReq;
import com.diary.api.response.DiaryRes;
import com.diary.api.response.NoteRes;
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
    NoteRepository noteRepository;

    @Autowired
    NoteRepositorySupport noteRepositorySupport;

    @Autowired
    UserDiaryRepository userDiaryRepository;

    @Autowired
    UserRepository userRepository;

    // 일기장 생성
    @Override
    public DiaryRes createDiary(User user, DiaryReq diaryReq) {
        DiaryCover coverId = diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get();
        String diaryTitle = diaryReq.getDiaryTitle();

        Diary diary = new Diary();
        diary.setDiaryCover(coverId);
        diary.setDiaryTitle(diaryTitle);
        diary.setUser(user);
        diary.setDiaryCreatedDate(LocalDate.now());
        diaryRepository.save(diary);

        UserDiary userDiary = new UserDiary();
        userDiary.setDiary(diaryRepository.getOne(diary.getId()));
        userDiary.setUser(user);
        userDiaryRepository.save(userDiary);

        return new DiaryRes(diary);
    }

    //일기장 수정
    @Override
    public DiaryRes updateDiary(Long id, DiaryReq diaryReq) {
        Diary diary = diaryRepository.getOne(id);
        diary.setDiaryCover(diaryRepositorySupport.getDiaryCover(diaryReq.getCoverId()).get());
        diary.setDiaryTitle(diaryReq.getDiaryTitle());
        diaryRepository.save(diary);

        return new DiaryRes(diary);
    }

    // 내 일기장 전체 조회, 나에게 공유된 일기장도 조회
    @Override
    public List<DiaryRes> getDiaryList(User user) {
        List<DiaryRes> diaryResList = null;
        List<Diary> diaryList = diaryRepository.findAllByUser(user);

        // 나에게 되어있는 다이어리도 가져오기
        List<UserDiary> userDiaryList = userDiaryRepository.findByGuestId(user.getUserId());
        for (UserDiary userDiary : userDiaryList) {
            diaryList.add(userDiary.getDiary());
        }
        // 다이어리 리턴값에맞춰서 변경
        if (diaryList.size() != 0) {
            diaryResList = convertToDiaryRes(diaryList, user);
        }
        return diaryResList;
    }

    // 일기장 한개 조회
    @Override
    public List<NoteRes> getDiary(Long id) {
        List<NoteRes> noteResList = new ArrayList<>();

        Diary diary = diaryRepository.getOne(id);
        List<Note> notes = noteRepository.findAllByDiary(diary);

        for (Note note: notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
            noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        return noteResList;
    }

    // 일기장 삭제
    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    // 일기장을 필요정보만 리턴
    public List<DiaryRes> convertToDiaryRes(List<Diary> diaries, User user) {
        List<DiaryRes> diaryResList = new ArrayList<>();
        for (Diary diary : diaries) {
            // 공유다이어리인경우 guest찾아서 닉네임으로 넣어주기
            String guestId = userDiaryRepository.getOne(diary.getId()).getGuestId();
            String guestNickname = userRepository.findByUserId(guestId).get().getUserNickname();
            DiaryRes diaryRes = new DiaryRes(diary);
            if (!guestId.isEmpty() && !guestId.equals(user.getUserId())) {
                diaryRes.setGuestId(guestNickname);
            }
            diaryResList.add(diaryRes);

        }
        return diaryResList;
    }
}
