package com.diary.api.service;

import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.db.repository.NotificationRepository;
import com.diary.api.db.repository.NotificationRepositorySupport;
import com.diary.api.request.NotificationReq;
import com.diary.api.response.AlarmDataSet;
import com.diary.api.response.NotificationDetailRes;
import com.diary.api.response.NotificationRes;
import com.diary.api.response.StreamDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.reflections.Reflections.log;

@Service
public class NotificationServiceImpl implements NotificationService{
    private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();
    private static final Map<String, String> CONNECTED_USERS = new ConcurrentHashMap<>();

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationRepositorySupport notificationRepositorySupport;

    @Override
    public void addEmitter(String uuid, AlarmDataSet alarmDataSet) {
//        if (CONNECTED_USERS.containsKey(alarmDataSet.getUserId())) { // 이미 기존에 아이디로 알림 채널이 연결되어 있으면
//            String prevUuid = CONNECTED_USERS.get(alarmDataSet.getUserId());
//            removeEmitter(prevUuid); // 이전 연결을 제거해준다.
//        }
        CLIENTS.put(uuid, alarmDataSet.getEmitter());
        CONNECTED_USERS.put(alarmDataSet.getUserId(), uuid);
        log.info("구독자 추가할 때 map size : " + CLIENTS.size());
    }

    @Override
    public void removeEmitter(String uuid) {
        CLIENTS.remove(uuid);
    }

    @Override
    public void removeUser(String userId) {
        CONNECTED_USERS.remove(userId);
    }

    @Override
    public List<NotificationRes> getNotifications(User user) {
        List<NotificationRes> notificationResList = new ArrayList<>();
        if (notificationRepositorySupport.findAllByUserId(user).isPresent()) {
            List<Notification> list = notificationRepositorySupport.findAllByUserId(user).get();

            for (Notification notification : list) {
                notificationResList.add(NotificationRes.of(notification));
            }
        }
        return notificationResList;
    }

    @Override
    public boolean updateNotificationRead(User user, long notificationId) {
        return notificationRepositorySupport.updateNotificationRead(user, notificationId);
    }

    @Override
    public void createNotification(NotificationReq notificationReq) {
        Notification notification = new Notification();
        notification.setNotificationContent(notificationReq.getNotificationContent());
        notification.setSenderImageUrl(notificationReq.getSenderImageUrl());
        notification.setUser(notificationReq.getUser());
        notification.setNotificationInfo(notificationReq.getNotificationInfo());
        notificationRepository.save(notification);
    }

    @Override
    public void publish(String message) {
//        log.info("알림 내용 : " + message);
//        Set<String> deadIds = new HashSet<>();
//        log.info("알림 발생 시점에서 map 개 수 : " + CLIENTS.size());
//        CLIENTS.forEach((id, emitter) -> {
//            try {
//                emitter.send(message, MediaType.APPLICATION_JSON);
//                log.info("알림 클라이언트로 보냄");
//            } catch (Exception e) {
//                deadIds.add(id);
//                log.error("* 에러가 발생해서 삭제 목록에 추가함 *");
//            }
//        });
//
//        deadIds.forEach(CLIENTS::remove);
    }

    @Override
    public void publishToUsers(NotificationDetailRes notificationDetailRes, List<String> userIdList) {

        Set<String> deadUuids = new HashSet<>();
        Set<String> deadUserIds = new HashSet<>();
        log.info("알림 발생 시점에서 map 개 수 : " + CLIENTS.size());

        log.info("초대 받는 사람 객체 : " + userIdList);

        userIdList.forEach((userId) -> {
            System.out.println("connected user 찍어보기 " + CONNECTED_USERS);
            System.out.println("userId 찍어보기 " + userId);
            if (!CONNECTED_USERS.containsKey(userId)) { // 연결되지 않았는데 알림을 보내라고 하는 경우
                log.info(userId + "는 연결되지 않았으므로 알림을 보내지 않는다."); // 현재 로그인 상태가 아니라는 것.
                return;
            }

            String uuid = CONNECTED_USERS.get(userId);
            try {
                SseEmitter emitter = CLIENTS.get(uuid);
                emitter.send(notificationDetailRes, MediaType.APPLICATION_JSON);
                log.info("알림 클라이언트로 보냄");
            } catch (Exception e) {
                deadUuids.add(uuid);
                deadUserIds.add(userId);
                log.error("* 에러가 발생해서 삭제 목록에 추가함 *");
            }
        });

        deadUuids.forEach(CLIENTS::remove); // 문제가 발생한 채널 제거
        deadUserIds.forEach(CONNECTED_USERS::remove);
    }


}
