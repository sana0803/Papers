package com.diary.api.service;

import com.diary.api.request.NoteReq;
import com.diary.api.response.NoteRes;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    public List<NoteRes> getMonthNote(int month, Long diaryId);
    public NoteRes getNote(Long noteId);
    public NoteRes setNote(NoteReq noteReq);
}
