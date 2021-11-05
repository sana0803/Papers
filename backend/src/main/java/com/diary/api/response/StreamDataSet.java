package com.diary.api.response;

import com.diary.api.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
@Setter
@AllArgsConstructor
public class StreamDataSet {
    User user;
    SseEmitter emitter;
}
