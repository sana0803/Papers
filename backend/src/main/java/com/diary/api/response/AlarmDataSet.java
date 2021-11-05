package com.diary.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.websocket.server.ServerEndpoint;

@Getter
@Setter
@AllArgsConstructor
public class AlarmDataSet {
    String userId;
    SseEmitter emitter;
}
