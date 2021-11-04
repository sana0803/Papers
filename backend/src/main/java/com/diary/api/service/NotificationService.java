package com.diary.api.service;

import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.response.StreamDataSet;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.reflections.Reflections.log;

@Service
public class NotificationService {
    private final ConcurrentHashMap<String, StreamDataSet> eventMap = new ConcurrentHashMap<>();

    public void addEmitter(final String UNIQUE_UUID, final StreamDataSet dataSet) { // 관리하는 맵에 uuid를 등록한다.
        log.info("map에 uuid 추가됨 : " + UNIQUE_UUID);
        eventMap.put(UNIQUE_UUID, dataSet);
        fetch();
    }

    public void removeEmitter(final String UNIQUE_UUID) { // 비동기 요청이 완료되면
        log.info("map에 uuid 삭제됨 : " + UNIQUE_UUID);
        Object deletedItem = eventMap.remove(UNIQUE_UUID);
        log.info("삭제된 객체 : " + deletedItem);
    }

    @Scheduled(initialDelay = 2000L, fixedDelay = 5000L)
    public void fetch() {
        if (eventMap.size() == 0) {
            System.out.println("map 사이즈가 0임");
            return;
        }
        System.out.println("fetch 돌아감");
        this.handleAlert();
    }

    @Transactional
    public void handleAlert() {

        List<String> toBeRemoved = new ArrayList<>(eventMap.size());
        List<Long> alertIdList = new ArrayList<>();
        System.out.println("알림 이벤트 핸들러가 수행됨 : " + eventMap.entrySet().size());
        for (Map.Entry<String, StreamDataSet> entry : eventMap.entrySet()) {

            final String uniqueKey = entry.getKey();
            System.out.println(uniqueKey);
            final StreamDataSet dataSet = entry.getValue();

            final User user = dataSet.getUser();
//            final List<Notification> receivingAlert = notificationRepository.findByNotificationTargetUserUidAndIsReadIsFalse(user.getUid());
//            final int noneReadCount = receivingAlert.size();
//
//            /** 접속 유저가 읽지 않은 알람의 개수 **/
//            if (noneReadCount == 0) {
//                continue;
//            }

            final SseEmitter emitter = dataSet.getEmitter();

            /** 30분 이내에 작성된 알람 목록 확인 **/
//            final List<Notification> alertList = getListAnMinuteAndAlertFalse(receivingAlert);

//            if (alertList.size() == 0) {
//                continue;
//            }

            /** 알림데이터 생성 **/
//            NotificationAlert alert = NotificationAlert.builder()
//                    .uid(user.getUid())
//                    .notificationCount(noneReadCount)
//                    .notifications(alertList)
//                    .build();


            /** 알림 목록 ID 획득 **/
//            alertIdList.addAll(alertList.stream()
//                    .map(Notification::getId)
//                    .collect(Collectors.toList()));

            try {
                System.out.println("알림 전송이 수행됨");
                /** 알림 전송 수행 **/
                emitter.send("알림이 왔습니당.", MediaType.APPLICATION_JSON_UTF8);

            } catch (Exception e) {
                log.error("이미터 센드 시 에러 발생 :: {}", e.getMessage());
                toBeRemoved.add(uniqueKey);
            }

        } // for

        /** 전송된 알람들 IS_ALERT 'Y' 로 변경 **/
//        updateIsAlert(alertIdList);

        /** 전송 오류 SseEmitter 제거 **/
        for (String uuid : toBeRemoved) {
            eventMap.remove(uuid);
        }
    }
}
