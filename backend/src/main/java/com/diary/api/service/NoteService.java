package com.diary.api.service;

import com.diary.api.request.NoteReq;
import com.diary.api.response.NoteRes;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    public List<NoteRes> getMonthNote(int month, Long diaryId);
    public NoteRes getNote(Long noteId);
    public NoteRes setNote(Long noteId, NoteReq noteReq);
    public NoteRes registNote(NoteReq noteReq);
    public NoteRes updateNote(Long noteId, NoteReq noteReq);
    public boolean deleteNote(Long noteId);
    public boolean changeDiaryNote(List<Long> notes, Long diaryId, Authentication authentication);
    public List<String> getImageFiles(String userId, Long diaryId);
}
