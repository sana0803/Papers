package com.diary.api.service;

import com.diary.api.db.entity.*;
import com.diary.api.db.repository.*;
import com.diary.api.request.NoteEmotionReq;
import com.diary.api.request.NoteReq;
import com.diary.api.request.NoteStickerReq;
import com.diary.api.response.BaseResponseBody;
import com.diary.api.response.NoteRes;
import com.diary.common.util.JwtTokenUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    NoteRepositorySupport noteRepositorySupport;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteHashtagRepository noteHashtagRepository;

    @Autowired
    NoteMediaRepository noteMediaRepository;

    @Autowired
    NoteStickerRepository noteStickerRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    EmotionRepository emotionRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmotionLogRepository emotionLogRepository;

    @Autowired
    EmotionLogRepositorySupport emotionLogRepositorySupport;

    public List<NoteRes> getNoteList(String userId) {
        List<Note> notes = null;
        if(noteRepositorySupport.getNoteList(userId).isPresent())
            notes = noteRepositorySupport.getNoteList(userId).get();
        List<NoteRes> noteResList = new ArrayList<>();
        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
            noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        if(noteResList == null) return null;
        return noteResList;
    }

    // 월별 일기 목록 조회
    @Override
    public List<NoteRes> getMonthNote(int month, String userId){
        List<NoteRes> noteResList = new ArrayList<>();
        List<Note> notes = null;

        if(noteRepositorySupport.getMonthNote(month, userId).isPresent())
            notes = noteRepositorySupport.getMonthNote(month, userId).get();
        else return null;

        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
            noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        return noteResList;
    }

    // 일기 하루 조회
    @Override
    public NoteRes getNote(Long noteId) {
        Note note = null;
        if(noteRepositorySupport.getNote(noteId).isPresent())
             note = noteRepositorySupport.getNote(noteId).get();
        else return null;

        NoteRes noteRes = new NoteRes(note);
        noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
        noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
        noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
        noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
        return noteRes;
    }

    // 일기 작성
    @Override
    public NoteRes setNote(Long noteId, NoteReq noteReq){

        Note note = new Note();
        if(noteId != null)
            if(noteRepositorySupport.getNote(noteId).isPresent())
                note = noteRepositorySupport.getNote(noteId).get();

        note.setNoteContent(noteReq.getNoteContent());
        note.setNoteDesign(noteRepositorySupport.getNoteDesign(noteReq.getDesignId()).get());
        note.setNoteLayout(noteRepositorySupport.getNoteLayout(noteReq.getLayoutId()).get());
        note.setNoteTitle(noteReq.getNoteTitle());
        note.setNoteContent(noteReq.getNoteContent());
//        note.setNoteCreateDate(LocalDate.now());
//        note.setNoteCreateTime(LocalTime.now());
        note.setDiary(diaryRepository.findById(noteReq.getDiaryId()).get());
        note.setFont(noteRepositorySupport.getFont(noteReq.getFontId()).get());
        note.setUser(userRepository.findByUserId(noteReq.getWriterId()).get());
        noteRepository.save(note);

        noteRepositorySupport.deleteNoteHashtag(note.getId());
        for(String hashtag : noteReq.getNoteHashtagList()){
            NoteHashtag noteHashtag = new NoteHashtag();
            noteHashtag.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteHashtag.setTagValue(hashtag);
            noteHashtagRepository.save(noteHashtag);
        }

        noteRepositorySupport.deleteNoteMedia(note.getId());

        noteRepositorySupport.setNoteMedias(noteReq.getNoteMediaList(), noteReq.getWriterId(), note.getDiary().getId());

        for(MultipartFile multipartFile : noteReq.getNoteMediaList()) {
            NoteMedia noteMedia = new NoteMedia();
            noteMedia.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteMedia.setMediaUrl("https://papers-bucket.s3.amazonaws.com/diary-file/"
                    + noteReq.getWriterId() + "/" + noteReq.getDiaryId() + "/" + multipartFile.getOriginalFilename());
            noteMedia.setMediaExtension(multipartFile.getOriginalFilename()
                    .substring(multipartFile.getOriginalFilename().lastIndexOf(".")));
            noteMediaRepository.save(noteMedia);
        }

        for(String media : noteReq.getNoteS3MediaList()){
            NoteMedia noteMedia = new NoteMedia();
            noteMedia.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteMedia.setMediaUrl(media);
            noteMedia.setMediaExtension("." + media.split("\\.")[media.split("\\.").length - 1]);
            noteMediaRepository.save(noteMedia);
        }

        noteRepositorySupport.deleteNoteSticker(note.getId());
        for(NoteStickerReq sticker : noteReq.getStickerList()){
            NoteSticker noteSticker = new NoteSticker();
            noteSticker.setSticker(noteRepositorySupport.getSticker(sticker.getStickerId()).get());
            noteSticker.setNote(noteRepositorySupport.getNote(note.getId()).get());
            noteSticker.setTopPixel(sticker.getTopPixel());
            noteSticker.setLeftPixel(sticker.getLeftPixel());
            noteStickerRepository.save(noteSticker);
        }

        noteRepositorySupport.deleteNoteEmotion(note.getId());
        if(noteReq.getEmotionList().size() > 0) {
            for (NoteEmotionReq noteEmotionReq : noteReq.getEmotionList()) {
                this.setNoteEmotion(noteEmotionReq);
            }
        }

        NoteRes noteRes = new NoteRes(note);
        noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
        noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
        noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
        noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
        return noteRes;
    }

    // 일기 작성
    public NoteRes registNote(NoteReq noteReq){
        User user = userRepository.findByUserId(noteReq.getWriterId()).get();
        // 일기작성 마일리지 10점 적립
        userService.updateMileage(user, user.getUserMileage() + 10);
        return this.setNote(null, noteReq);
    }
    // 일기 수정
    public NoteRes updateNote(Long noteId, NoteReq noteReq) {
        return this.setNote(noteId, noteReq);
    }

    //  일기 삭제
    @OnDelete(action = OnDeleteAction.CASCADE)
    public boolean deleteNote(Long noteId) {
        try {
            if(noteRepositorySupport.getNote(noteId).isPresent())
                noteRepository.delete(noteRepositorySupport.getNote(noteId).get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 일기들 일기장 옮기기
    public boolean changeDiaryNote(List<Long> notes, Long diaryId, Authentication authentication) {

        User user = JwtTokenUtil.getUser(authentication, userService);
//        try {
            for(Long noteId : notes) {
                Note note = noteRepositorySupport.getNote(noteId).get();
                if(user.getUserId() != note.getUser().getUserId()) return false;

                note.setDiary(diaryRepository.getOne(diaryId));
                noteRepository.save(note);
            }
            return true;
//        } catch (Exception e) {
//            return false;
//        }
    }

    public List<String> getImageFiles(String userId, Long diaryId){
        return noteRepositorySupport.getImageFiles(userId, diaryId);
    }

    // 일기에 감정표현 남기기
    public boolean setNoteEmotion(NoteEmotionReq noteEmotionReq){
        try {
            Emotion emotion = new Emotion();
            User user = userRepository.findByUserId(noteEmotionReq.getWriterId()).get();
            emotion.setEmotionInfo(noteRepositorySupport.getEmotionInfo(noteEmotionReq.getEmotionInfoId()).get());
            emotion.setNote(noteRepositorySupport.getNote(noteEmotionReq.getNoteId()).get());
            emotion.setUser(user);
            emotionRepository.save(emotion);

            List<EmotionLog> emotionLogList = emotionLogRepositorySupport.getEmotionLog(
                    noteEmotionReq.getNoteId(),
                    user.getUserId()).get();
            if(emotionLogList.size() == 0) {
                emotionLogRepository.save(new EmotionLog(user, noteRepositorySupport.getNote(noteEmotionReq.getNoteId()).get()));
                userService.updateMileage(user, user.getUserMileage() + 2);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 감정표현 취소
    public boolean deleteNoteEmotion(NoteEmotionReq noteEmotionReq) {
        try {
            noteRepositorySupport.deleteNoteEmotionByUser(noteEmotionReq);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<NoteRes> getHashtagNotes(String hashtag, String userId) {
        List<Note> notes = null;
        if(noteRepositorySupport.getHashtagNotes(hashtag, userId).isPresent())
            notes = noteRepositorySupport.getHashtagNotes(hashtag, userId).get();
        List<NoteRes> noteResList = new ArrayList<>();
        for(Note note : notes) {
            NoteRes noteRes = new NoteRes(note);
            noteRes.setNoteSticker(noteRepositorySupport.getNoteStickers(note.getId()).get());
            noteRes.setNoteEmotion(noteRepositorySupport.getNoteEmotions(note.getId()).get());
            noteRes.setNoteHashtag(noteRepositorySupport.getNoteHashtags(note.getId()).get());
            noteRes.setNoteMedia(noteRepositorySupport.getNoteMedias(note.getId()).get());
            noteResList.add(noteRes);
        }
        if(noteResList == null) return null;
        return noteResList;
    }

    public List<String> getHashtagList(String userId){
        if(noteRepositorySupport.getHashtagList(userId).isPresent())
            return noteRepositorySupport.getHashtagList(userId).get();
        return null;
    }
}
