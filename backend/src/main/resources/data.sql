insert into papersdb.note_design values (1, 'designurl1') ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.user values ('test1', 50, '홍길동', '의적', 'userprofile1', 'testpwd1') ON DUPLICATE KEY UPDATE user_id='test1';
insert into papersdb.font values (1, 'font1', 50, 'fonturl1') ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.note_layout values (1) ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.sticker_package values (1, 'package1', 50) ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.diary_cover values (1, 'cover1', 500, 'test.com') ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.diary values (1, sysdate(), 'desc1', 'diarytitle1', 1, 'test1') ON DUPLICATE KEY UPDATE id=1;

insert into papersdb.note (id, note_content, note_create_date, note_create_time, note_title, diary_id, font_id, design_id, layout_id, writer_id)
 values (1, 'content1', sysdate(), localtime(), 'title1', 1, 1, 1, 1, 'test1') ON DUPLICATE KEY UPDATE id = 1;
insert into papersdb.sticker values (1, 'stickerurl1', 1) ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.note_hashtag values (1, '좋아요', 1) ON DUPLICATE KEY UPDATE id=1;
insert into papersdb.note_media values (1, '.jpg', 'mediaurl1', 1) ON DUPLICATE KEY UPDATE id=1;