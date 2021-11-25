package com.diary.api.service;

import com.diary.api.db.entity.Emotion;
import com.diary.api.request.KakaoReq;
import com.diary.api.request.NoteEmotionReq;
import com.diary.api.request.NoteReq;
import com.diary.api.response.NoteRes;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NoteService {

    public List<NoteRes> getNoteList(String userId);
    public List<NoteRes> getMonthNote(int month, String userId);
    public NoteRes getNote(Long noteId);
    public NoteRes setNote(Long noteId, NoteReq noteReq);
    public NoteRes registNote(NoteReq noteReq);
    public NoteRes updateNote(Long noteId, NoteReq noteReq);
    public boolean deleteNote(Long noteId);
    public boolean changeDiaryNote(List<Long> notes, Long diaryId, Authentication authentication);
    public List<String> getImageFiles(String userId, Long diaryId);
    public boolean setNoteEmotion(NoteEmotionReq noteEmotionReq);
    public boolean deleteNoteEmotion(NoteEmotionReq noteEmotionReq);
    public List<NoteRes> getHashtagNotes(String hashtag, String userId);
    public List<String> getHashtagList(String userId);
    public boolean setImageFiles(KakaoReq kakaoReq);
    public List<String> getKakaoImageFiles(String userId);
}
