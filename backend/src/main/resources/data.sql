-- 노트 디자인
insert into note_design values (1, 'design-url-1') ON DUPLICATE KEY UPDATE id=1;

-- 노트 레이아웃
insert into note_layout values (1) ON DUPLICATE KEY UPDATE id=1;

-- 사용자
insert into user values ('test1', 1000, '의적', 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/dummy-user/test1/%EC%9D%98%EC%A0%81.png', '$2a$10$9RbUeMd5zBbv1bic5Me.euAyCi1fqlwbWhKfmn0k3D7bnqJCtJhx.') ON DUPLICATE KEY UPDATE user_id='test1';
insert into user values ('test2', 1000, '로봇', 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/dummy-user/test2/%EB%A1%9C%EB%B4%87.jpg', '$2a$10$9RbUeMd5zBbv1bic5Me.euAyCi1fqlwbWhKfmn0k3D7bnqJCtJhx.') ON DUPLICATE KEY UPDATE user_id='test2';
insert into user values ('test3', 1000, '카누', 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/dummy-user/test3/%EC%B9%B4%EB%88%84.jpg', '$2a$10$9RbUeMd5zBbv1bic5Me.euAyCi1fqlwbWhKfmn0k3D7bnqJCtJhx.') ON DUPLICATE KEY UPDATE user_id='test3';

-- 폰트
insert into font values (1, '기본체', 0, 'Pretendard Variable') ON DUPLICATE KEY UPDATE id=1;
insert into font values (2, '코트라 희망체', 50, 'KOTRAHOPE') ON DUPLICATE KEY UPDATE id=2;
insert into font values (3, '교보 손글씨체', 50, 'KyoboHand') ON DUPLICATE KEY UPDATE id=3;
insert into font values (4, '완도 희망체', 50, 'WandohopeB') ON DUPLICATE KEY UPDATE id=4;
insert into font values (5, '카페24 써라운드에어', 50, 'Cafe24SsurroundAir') ON DUPLICATE KEY UPDATE id=5;
insert into font values (6, '티웨이하늘체', 50, 'twaysky') ON DUPLICATE KEY UPDATE id=6;
insert into font values (7, 'Y이드스트릿체', 50, 'YdestreetB') ON DUPLICATE KEY UPDATE id=7;
insert into font values (8, '정묵 바위체', 50, 'SangSangRock') ON DUPLICATE KEY UPDATE id=8;
insert into font values (9, '서평원 꺾깎체', 50, 'SLEIGothicTTF') ON DUPLICATE KEY UPDATE id=9;
insert into font values (10, '어비 깡자체', 50, 'UhBeeKang-Ja') ON DUPLICATE KEY UPDATE id=10;
insert into font values (11, '조선일보 명조체', 50, 'Chosunilbo_myungjo') ON DUPLICATE KEY UPDATE id=11;
insert into font values (12, '경기천년바탕체', 50, 'GyeonggiBatang') ON DUPLICATE KEY UPDATE id=12;
insert into font values (13, '가나 초콜릿체', 50, 'ghanachoco') ON DUPLICATE KEY UPDATE id=13;
insert into font values (14, '도스명조체', 50, 'DOSMyungjo') ON DUPLICATE KEY UPDATE id=14;
insert into font values (15, '서울한강체', 50, 'SeoulHangangM') ON DUPLICATE KEY UPDATE id=15;
insert into font values (16, '어그로체', 50, 'SBAggroB') ON DUPLICATE KEY UPDATE id=16;
insert into font values (17, '둥근모꼴', 50, 'DungGeunMo') ON DUPLICATE KEY UPDATE id=17;
insert into font values (18, '웰컴체', 50, 'OTWelcomeRA') ON DUPLICATE KEY UPDATE id=18;
insert into font values (19, '고운돋음', 50, 'GowunDodum-Regular') ON DUPLICATE KEY UPDATE id=19;
insert into font values (20, '망고빙수', 50, 'SF_IceMango') ON DUPLICATE KEY UPDATE id=20;


-- 유저 폰트
insert into user_font values (1, 3, 'test1') ON DUPLICATE KEY UPDATE id=1;
insert into user_font values (2, 2, 'test1') ON DUPLICATE KEY UPDATE id=2;
insert into user_font values (3, 3, 'test2') ON DUPLICATE KEY UPDATE id=3;

-- 스티커 패키지
insert into sticker_package values (1, 'pack1', 50) ON DUPLICATE KEY UPDATE id=1;
insert into sticker_package values (2, 'Circle', 50) ON DUPLICATE KEY UPDATE id=2;
insert into sticker_package values (3, 'star', 50) ON DUPLICATE KEY UPDATE id=3;
insert into sticker_package values (4, 'Heart', 50) ON DUPLICATE KEY UPDATE id=4;

-- 다이어리 커버
insert into diary_cover values (1, '기본 살구색 커버', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/base-cover/basic_cover1.jpg') ON DUPLICATE KEY UPDATE id=1;
insert into diary_cover values (2, '기본 민트색 커버', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/base-cover/basic_cover2.jpg') ON DUPLICATE KEY UPDATE id=2;
insert into diary_cover values (3, '기본 검은색 커버', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/base-cover/basic_cover3.jpg') ON DUPLICATE KEY UPDATE id=3;
insert into diary_cover values (4, '기본 체크 커버', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/base-cover/basic_cover4.jpg') ON DUPLICATE KEY UPDATE id=4;
insert into diary_cover values (5, '알록 달록 포장지', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover1.jpg') ON DUPLICATE KEY UPDATE id=5;
insert into diary_cover values (6, '바다위에 올챙이', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover2.jpg') ON DUPLICATE KEY UPDATE id=6;
insert into diary_cover values (7, '별 사탕 팡팡', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover3.jpg') ON DUPLICATE KEY UPDATE id=7;
insert into diary_cover values (8, '케익 좋아하는 사람?', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover4.jpg') ON DUPLICATE KEY UPDATE id=8;
insert into diary_cover values (9, '구겨놓은 종이', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover5.jpg') ON DUPLICATE KEY UPDATE id=9;
insert into diary_cover values (10, '파랑색 식탁보', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover6.jpg') ON DUPLICATE KEY UPDATE id=10;
insert into diary_cover values (11, '분홍색 식탁보', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover7.jpg') ON DUPLICATE KEY UPDATE id=11;
insert into diary_cover values (12, '커버명 : - 미 정 -', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover8.jpg') ON DUPLICATE KEY UPDATE id=12;
insert into diary_cover values (13, '할아부지 식탁보', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover9.jpg') ON DUPLICATE KEY UPDATE id=13;
insert into diary_cover values (14, '한정판일 것 같은 커버', 150, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/diary-cover/store_cover10.jpg') ON DUPLICATE KEY UPDATE id=14;

-- 다이어리
insert into diary values (1, sysdate(), '기본 일기장', 1, 'test1') ON DUPLICATE KEY UPDATE id=1;
insert into diary values (2, sysdate(), '기본 일기장', 1, 'test2') ON DUPLICATE KEY UPDATE id=2;
insert into diary values (3, sysdate(), '기본 일기장', 1, 'test3') ON DUPLICATE KEY UPDATE id=3;

-- 노트
--insert into note (id, note_content, note_create_date, note_create_time, note_title, diary_id, font_id, design_id, layout_id, writer_id)
-- values (1, 'content1', sysdate(), localtime(), 'title1', 1, 1, 1, 1, 'test1') ON DUPLICATE KEY UPDATE id = 1;

-- 스티커
insert into sticker values (1, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/pack1/sticker1.png', 1) ON DUPLICATE KEY UPDATE id=1;
insert into sticker values (2, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/pack1/sticker2.png', 1) ON DUPLICATE KEY UPDATE id=2;
insert into sticker values (3, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/pack1/sticker3.png', 1) ON DUPLICATE KEY UPDATE id=3;
insert into sticker values (4, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/pack1/sticker4.png', 1) ON DUPLICATE KEY UPDATE id=4;
insert into sticker values (5, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/pack1/sticker5.png', 1) ON DUPLICATE KEY UPDATE id=5;
insert into sticker values (6, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/pack1/sticker6.png', 1) ON DUPLICATE KEY UPDATE id=6;

insert into sticker values (7, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Circle/circle1.png', 2) ON DUPLICATE KEY UPDATE id=7;
insert into sticker values (8, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Circle/circle2.png', 2) ON DUPLICATE KEY UPDATE id=8;
insert into sticker values (9, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Circle/circle3.png', 2) ON DUPLICATE KEY UPDATE id=9;
insert into sticker values (10, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Circle/circle4.png', 2) ON DUPLICATE KEY UPDATE id=10;
insert into sticker values (11, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Circle/circle5.png', 2) ON DUPLICATE KEY UPDATE id=11;
insert into sticker values (12, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Circle/circle6.png', 2) ON DUPLICATE KEY UPDATE id=12;

insert into sticker values (13, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/star/star1.png', 3) ON DUPLICATE KEY UPDATE id=13;
insert into sticker values (14, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/star/star2.png', 3) ON DUPLICATE KEY UPDATE id=14;
insert into sticker values (15, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/star/star3.png', 3) ON DUPLICATE KEY UPDATE id=15;
insert into sticker values (16, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/star/star4.png', 3) ON DUPLICATE KEY UPDATE id=16;
insert into sticker values (17, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/star/star5.png', 3) ON DUPLICATE KEY UPDATE id=17;
insert into sticker values (18, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/star/star6.png', 3) ON DUPLICATE KEY UPDATE id=18;

insert into sticker values (19, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Heart/heart1.png', 4) ON DUPLICATE KEY UPDATE id=19;
insert into sticker values (20, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Heart/heart2.png', 4) ON DUPLICATE KEY UPDATE id=20;
insert into sticker values (21, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Heart/heart3.png', 4) ON DUPLICATE KEY UPDATE id=21;
insert into sticker values (22, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Heart/heart4.png', 4) ON DUPLICATE KEY UPDATE id=22;
insert into sticker values (23, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Heart/heart6.png', 4) ON DUPLICATE KEY UPDATE id=23;
insert into sticker values (24, 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/store/sticker/Heart/heart5.png', 4) ON DUPLICATE KEY UPDATE id=24;

-- 유저 스티커 패키지
--insert into user_sticker_package values (1, 1, 'test1') ON DUPLICATE KEY UPDATE id=1;

-- 노트 해시태그
--insert into note_hashtag values (1, '좋아요', 1) ON DUPLICATE KEY UPDATE id=1;

-- 노트 미디어
--insert into note_media values (1, '.jpg', 'media-url-1', 1) ON DUPLICATE KEY UPDATE id=1;

-- 다이어리 커버
insert into diary_cover values (1, '다이어리 커버1', 600, 'img url-1') ON DUPLICATE KEY UPDATE id=1;
insert into diary_cover values (2, '다이어리 커버2', 600, 'img url-2') ON DUPLICATE KEY UPDATE id=2;
insert into diary_cover values (3, '다이어리 커버3', 600, 'img url-3') ON DUPLICATE KEY UPDATE id=3;

-- 유저 다이어리 커버
insert into user_diary_cover values (1, 1, 'test1') ON DUPLICATE KEY UPDATE id=1;
insert into user_diary_cover values (2, 2, 'test1') ON DUPLICATE KEY UPDATE id=2;
insert into user_diary_cover values (3, 3, 'test1') ON DUPLICATE KEY UPDATE id=3;
insert into user_diary_cover values (4, 4, 'test1') ON DUPLICATE KEY UPDATE id=4;
insert into user_diary_cover values (5, 1, 'test2') ON DUPLICATE KEY UPDATE id=5;
insert into user_diary_cover values (6, 2, 'test2') ON DUPLICATE KEY UPDATE id=6;
insert into user_diary_cover values (7, 3, 'test2') ON DUPLICATE KEY UPDATE id=7;
insert into user_diary_cover values (8, 4, 'test2') ON DUPLICATE KEY UPDATE id=8;
insert into user_diary_cover values (9, 1, 'test3') ON DUPLICATE KEY UPDATE id=9;
insert into user_diary_cover values (10, 2, 'test3') ON DUPLICATE KEY UPDATE id=10;
insert into user_diary_cover values (11, 3, 'test3') ON DUPLICATE KEY UPDATE id=11;
insert into user_diary_cover values (12, 4, 'test3') ON DUPLICATE KEY UPDATE id=12;

-- 노티피케이션 정보
insert into notification_info values (1, '일기 작성 알림') ON DUPLICATE KEY UPDATE id=1;
insert into notification_info values (2, '댓글 알림') ON DUPLICATE KEY UPDATE id=2;
insert into notification_info values (3, '초대 알림') ON DUPLICATE KEY UPDATE id=3;

-- 노티피케이션
--insert into notification values (1, 'giver님이 일기를 작성했습니다.', '2021-11-03', 0, '11:11:11', 'https://papers-bucket.s3.amazonaws.com/profile/test1/eb3df4c9-a031-42c9-9bb5-0d3562de7995landing-main.jpg', 1, 'test1') ON DUPLICATE KEY UPDATE id=1;
--insert into notification values (2, 'giver님이 댓글 작성했습니다.', '2021-11-03', 0, '11:11:11', 'https://papers-bucket.s3.amazonaws.com/profile/test1/eb3df4c9-a031-42c9-9bb5-0d3562de7995landing-main.jpg', 2, 'test1') ON DUPLICATE KEY UPDATE id=2;

-- 좋아요
insert into emotion_info values (1, '좋아요') ON DUPLICATE KEY UPDATE id=1;
insert into emotion_info values (2, '웃겨요') ON DUPLICATE KEY UPDATE id=2;
insert into emotion_info values (3, '슬퍼요') ON DUPLICATE KEY UPDATE id=3;