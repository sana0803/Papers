<template>
  <div style="display: relative;">
    <div id="Line"></div>
    <div v-if="noteList.length > 0">    
      <div      
        id="diary-list-item"
        v-for="note in viewList"
        :key="note.noteId"
      >
        <div id="diary-wrap">
          <div class="diary-content">
            <div class="diary-title-wrap">
              <div class="title-sec">
                <span class="diary-title">{{ note.noteTitle }}</span>
                <br>
                <span class="diary-writer">{{ note.noteId }}</span>
              </div>
              <div class="date-sec">
                <span class="diary-writer">{{ note.writerNickName}}</span>
                <span class="diary-date">
                  {{ note.noteCreatedDate.slice(2, 4)}}.{{ note.noteCreatedDate.slice(5, 7)}}.{{ note.noteCreatedDate.slice(8, 10)}}
                </span>
              </div>
            </div>
            <div id="horizon-line"></div>
            <div class="diary-text">
              <span>{{ note.noteContent }}
              </span>
            </div>
            <div class="diary-hashtag" v-for="(hashtag, idx) in note.noteHashtagList" :key="idx">
              <span>#{{ hashtag }}</span>
            </div>
            <div class="diary-img-wrap">
              <div v-if="note.noteMediaList.length > 1">
                <v-carousel
                  :show-arrows="true"
                  style="height:100%;">
                  <v-carousel-item
                    v-for="(img, i) in note.noteMediaList"
                    :key="i"
                    :src="img"
                    class="diary-content-img"
                  ></v-carousel-item>
                </v-carousel>
              </div>
              <div v-else-if="note.noteMediaList.length == 0">
              </div>
              <div v-else>
                <div style="position:relative;">
                  <img :src="note.noteMediaList[0]" class="diary-content-img" alt="일기 사진" />
                  <img v-for="sticker in note.noteStickerList" :key="sticker.id" :src="sticker.sticker.stickerUrl" :style="{top:sticker.topPixel, left:sticker.leftPixel}" class="sticker" />
                </div> 
              </div>
            </div>
          </div>
          <div class="diary-emotion">
            <div class="emotion-left">
              <div class="emo-item">
                <v-icon @click="clickHeart(note.noteId, note.emotionStatusRes.pressLike)" class="emo-icon" :class="{ 'like-clicked' : note.emotionStatusRes.pressLike }">favorite_border</v-icon>
                <div class="emotion-cnt">
                  <span>{{note.emotionStatusRes.likeEmotionCount}}</span>
                </div>
              </div>
              <div class="emo-item">
                <v-icon @click="clickSmile(note.noteId, note.emotionStatusRes.pressFunny)" class="emo-icon" :class="{ 'funny-clicked' : note.emotionStatusRes.pressFunny }">sentiment_very_satisfied</v-icon>
                <div class="emotion-cnt">
                  <span>{{note.emotionStatusRes.funnyEmotionCount}}</span>
                </div>
              </div>
              <div class="emo-item">
                <v-icon @click="clickSad(note.noteId, note.emotionStatusRes.pressSad)" class="emo-icon" :class="{ 'sad-clicked' : note.emotionStatusRes.pressSad }">sentiment_dissatisfied</v-icon>
                <div class="emotion-cnt">
                  <span>{{note.emotionStatusRes.sadEmotionCount}}</span>
                </div>
              </div>
            </div>
            <div class="emotion-right" v-if="note.writerId == loginUser.userId">
              <span @click="goUpdate(note)">수정</span>
              <span @click="onDialog(note)">삭제</span>
            </div>
            <div class="emotion-right" v-else>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else id="diary-empty">
      <div>
        <img src="../../assets/image/paper.png" style="width:90px; margin-bottom:24px;" />
      </div>
      <span>아직 작성한 일기가 없는 것 같아요.</span> <br>
      <span class="go-write-btn" @click="goWrite()">
        일기 작성하러 가기 &nbsp;&nbsp;>
      </span>
    </div>
    <!-- 일기 페이지네이션 -->
    <div id="diary-pagination">
      <v-pagination
        v-model="page"
        :length="Math.ceil(noteList.length/2)"
        @input="change"
        class="page-sec"
      ></v-pagination>
    </div>
    <!-- Dialog -->
    <v-dialog v-model="dialog" persistent max-width="286">
      <v-card id="Dialog">
        <div id="Dialog_Header">
          <v-icon @click="dialog = false" id="Dialog_Close" style="font-size: 1.5em">close</v-icon>
        </div>
        <div id="Dialog_Content">
          <div id="Dialog_Text">
            일기를 <span style="color: #ffb319">삭제</span>하시겠습니까?
          </div>
          <div id="Dialog_Btn_Box">
            <v-btn @click="onDeleteNote(note.noteId)" style="background: #ffb319; color: white" class="Dialog_Btn">삭제
            </v-btn>
            <v-btn @click="dialog = false" style="background: #9f9f9f; color: white" class="Dialog_Btn">취소</v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  import {
    mapState
  } from 'vuex';
  export default {
    data() {
      return {
        page: 1,
        dialog: false,
        noteList: [],
        viewList: [],
        hashtagList: [],
        note: [],
        emotionReq: {
          "emotionInfoId": 0,
          "noteId": 0,
          "diaryId": 0,
        },
      };
    },
    methods: {
      change(num){
      var temp = 0
        for(let i=1;i<=this.noteList.length;i++){
          if(i==num){
            this.viewList = []
            if(this.noteList.length>=temp+1){
              this.viewList.push(this.noteList[temp])
            }
            if(this.noteList.length>=temp+2){
              this.viewList.push(this.noteList[temp+1]) 
            }
            break
          }
          temp+=2
        }
      },
      clickHeart(noteId, isPress) {
        this.emotionReq.emotionInfoId = 1
        this.emotionReq.noteId = noteId
        if (!isPress) {
          this.emotionConfirm()
        } else {
          this.emotionCancel()
        }
      },
      clickSmile(noteId, isPress) {
        this.emotionReq.emotionInfoId = 2
        this.emotionReq.noteId = noteId
        if (!isPress) {
          this.emotionConfirm()
        } else {
          this.emotionCancel()
        }
      },
      clickSad(noteId, isPress) {
        this.emotionReq.emotionInfoId = 3
        this.emotionReq.noteId = noteId
        if (!isPress) {
          this.emotionConfirm()
        } else {
          this.emotionCancel()
        }
      },
      emotionConfirm() {
        this.$store.dispatch("emotionConfirm", this.emotionReq)
          .then(() => {
            this.$store.dispatch("getDiaryContent", this.currentDiary.id)
              .then((res) => {
                this.noteList = res.data.note.reverse();
                this.emotionReq.diaryId = this.noteList[0].diaryId
                this.change(this.page)
              })
          })
      },
      emotionCancel() {
        this.$store.dispatch("emotionCancel", this.emotionReq)
          .then(() => {
            this.$store.dispatch("getDiaryContent", this.currentDiary.id)
              .then((res) => {
                this.noteList = res.data.note.reverse();
                this.emotionReq.diaryId = this.noteList[0].diaryId
                this.change(this.page)
              })
          })
      },
      goWrite() {
        this.$store.commit('initNoteContent')
        this.$store.commit('setIsUpdate', false)
        this.$router.push("/write");
      },
      goUpdate(note) {
        const localNote = {
          noteId: note.noteId,
          diaryId: note.diaryId,
          fontId: note.fontId,
          layoutId: 1,
          designId: 1,
          writerId: note.writerId,
          noteTitle: note.noteTitle,
          noteContent: note.noteContent,
          noteS3MediaList: note.noteMediaList,
          noteMediaList: [],
          noteHashtagList: '#' + note.noteHashtagList[0],
          stickerList: note.noteStickerList,
          emotionList: note.emotionList
        }
        for (let i = 1; i < note.noteHashtagList.length; i++) {
          localNote.noteHashtagList += ('#' + note.noteHashtagList[i])
        }
        this.$store.commit('setNoteContent', localNote)
        this.$store.commit('setIsUpdate', true)
        this.$router.push("/write");
      },
      onDialog(note) {
        this.note = note;
        this.dialog = true;
      },
      onDeleteNote(id) {
        this.$store.dispatch("deleteNote", id)
          .then(() => {
            console.log('일기 삭제')
            this.dialog = false;
            this.$store.dispatch("getDiaryContent", this.currentDiary.id)
              .then((res) => {
                console.log(res.data)
                this.noteList = res.data.note.reverse();
                this.emotionReq.diaryId = this.noteList[0].diaryId
                this.page = 1
                this.viewList = []
                if(this.noteList.length>=1){
                  this.viewList.push(this.noteList[0])
                }
                if(this.noteList.length>=2){
                  this.viewList.push(this.noteList[1])
                }
              })
          })
      },
    },
    computed: {
      currentDiary() {
        return this.$store.getters.getCurrentDiary;
      },
      loginUser() {
        return this.$store.getters.getLoginUser;
      },
      ...mapState([
        'loginUser'
      ]),
    },
    created() {
      const diaryIdQuery = this.$route.query.diaryId
      const noteIdQuery = this.$route.query.noteId
      if (diaryIdQuery && noteIdQuery) {
        // alert('다이어리 아이디는 ' + diaryIdQuery)
        // alert('노트 아이디는 ' + noteIdQuery)
        this.$store.dispatch("getDiaryContent", diaryIdQuery)
        .then((res) => {
          this.noteList = res.data.note.reverse();
          this.emotionReq.diaryId = this.noteList[0].diaryId

          for (let i = 0; i < this.noteList.length; i++) {
            if (this.noteList[i].noteId === noteIdQuery) {
              this.page = parseInt(i / 2) + 1
              this.change(this.page)
              break
            }
          }
        })
        return
      }
      
      console.log(this.currentDiary);
      this.$store.dispatch("getDiaryContent", this.currentDiary.id)
        .then((res) => {
          this.noteList = res.data.note.reverse();
          this.emotionReq.diaryId = this.noteList[0].diaryId
          if(this.noteList.length>=1){
            this.viewList.push(this.noteList[0])
          }
          if(this.noteList.length>=2){
            this.viewList.push(this.noteList[1])
          }
        })
    },
  };
</script>

<style lang="scss" scoped>
#Line {
  height: 590px;
  border: 1px solid #e7e7e7;
  position: absolute;
  top: 71px;
  left: 461px;
}

#horizon-line {
  width: 100%;
  border-bottom: 1px solid #ccc;
  margin-bottom: 8px;
}

#diary-empty {
  width: 50%;
  font-size: 18px;
  text-align: center;
  margin-top: 140px;

  // background-color: #ffb319;
  span {
    color: #444;
  }

  .go-write-btn {
    display: inline-block;
    position: relative;
    overflow: hidden;
    cursor: pointer;
    width: 37%;
    color: #ffb319;
    margin-top: 24px;
    padding-bottom: 2px;
    font-weight: 600;
    // background-color: #eee;
  }

  .go-write-btn:after {
    position: absolute;
    content: '';
    width: 0;
    left: 0;
    height: 2px;
    bottom: 0px;
    background-color: #ffb319;
    // border-bottom: 1px solid #ffb319;
    transition: .25s;
  }

  .go-write-btn:hover {
    // border-bottom: 1px solid #ffb319;
    transition: .25s;
  }

  .go-write-btn:hover:after {
    width: 100%;
    left: 0;
  }
}

#diary-list-item {
  float: left;
}

#diary-list-item::after {
  // content: '';
  clear: both;
  display: block;
}

#diary-wrap {
  width: 371px;
  height: 538px;
  margin: 36px 36px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.diary-content {
  // min-height: 480px;
  max-height: 485px;
  // background-color: green;
  overflow: hidden;
}

.diary-title-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 3px;
}

.diary-title {
  font-size: 20px;
  font-weight: 500;
  line-height: 1.2;
}

.date-sec {
  // background-color: aquamarine;
  // margin-left: 3px;
  min-width: 50px;
  text-align: right;
}

.diary-writer {
  // color: #aaa;
  font-size: 14px;
  margin-right: 12px;
}

.diary-date {
  color: #929292;
  font-size: 14px;
}

.diary-text {
  margin-bottom: 12px;
  // background-color: lightblue;
  overflow: hidden;
  max-height: 95px;
  line-height: 1.4;
}

.diary-hashtag {
  display: inline-block;
  margin-right: 5px;
  font-weight: 500;

  span {
    color: #ffb319;
  }
}

.diary-img-wrap {
  margin-top: 18px;
  max-width: 100%;
  min-height: 250px;
  // max-height: 300px;
  overflow: hidden;
  background-color: #f7f7f7;
}
.go-write-btn:after {
  position: absolute;
  content: '';
  width: 0;
  left: 0;
  height: 2px;
  bottom: 0px;
  background-color: #ffb319;
  // border-bottom: 1px solid #ffb319;
  transition: .25s;
}
.go-write-btn:hover {
  // border-bottom: 1px solid #ffb319;
  transition: .25s;
}
.go-write-btn:hover:after {
  width: 100%;
  left: 0;
}

#diary-list-item {
  float: left;
}
#diary-list-item::after {
  // content: '';
  clear: both;
  display: block;
}
#diary-wrap {
  width: 371px;
  height: 538px;
  margin: 36px 36px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.diary-content {
  // min-height: 480px;
  max-height: 485px;
  // background-color: green;
  overflow: hidden;
}
.diary-title-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 3px;
}
.diary-title {
  font-size: 20px;
  font-weight: 500;
  line-height: 1.2;
}
.date-sec {
  // background-color: aquamarine;
  // margin-left: 3px;
  min-width: 50px;
  text-align: right;
}
.diary-writer {
  // color: #aaa;
  font-size: 14px;
  margin-right: 12px;
}
.diary-date {
  color: #929292;
  font-size: 14px;
}
.diary-text {
  margin-bottom: 12px;
  // background-color: lightblue;
  overflow-y: scroll;
  max-height: 95px;
  line-height: 1.4;
}
.diary-text::-webkit-scrollbar {
  display: none;
}
.diary-hashtag {
  display: inline-block;
  margin-right: 5px;
  font-weight: 500;    
  span {
    color: #ffb319;
  }
}
.diary-img-wrap {
  margin-top: 18px;
  max-width: 100%;
  min-height: 250px;
  // max-height: 300px;
  overflow: hidden;
  background-color: #f7f7f7;  
}
.diary-content-img {
  max-width: 100%;
  height: 100%;
  // background-color: aquamarine;
}
.diary-emotion {
  display: flex;
  justify-content: space-between;
}
.emotion-left {
  color: #929292;
  // background-color:cornflowerblue;
}
.emo-item {
  display: inline-block;
}
.emo-icon {
  cursor: pointer;
}
.emotion-cnt {
  display: inline-block;
  margin: 0 10px 0 5px;

  span {
    color: #929292;
  }
}
.emotion-right {
  display: inline-block;

  span {
    font-size: 14px;
    color: #9f9f9f;
    margin-left: 13px;
    cursor: pointer;
  }

  span:hover {
    color: #ffb319;
  }
}
#diary-pagination {  
  position: absolute;
  left: auto;
  bottom: -50px;
  width: 886px;
  overflow: hidden;
  background-color: #eee;
  margin-bottom: 20px;
}
// .page-sec {  
  // width: 100%;
  // height: 50px;
  // background-color: #ffb319;
  // }
#Dialog {
  height: 180px;
}
#Dialog_Header {
  height: 40px;
}
#Dialog_Close {
  float: right;
  margin-top: 5px;
  margin-right: 5px;
}
#Dialog_Text {
  width: 100%;
  height: 38px;
  line-height: 38px;
  // background-color: #eee;
  margin: 0 auto;
  margin-top: 5px;
  font-size: 16px;
  text-align: center;
}
#Dialog_Btn_Box {
  width: 164px;
  height: 32px;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  margin-top: 25px;
}
.Dialog_Btn {
  width: 76px;
  height: 32px;
  color: #585858;
}
.like-clicked {
  color: #E41D35;
}
.funny-clicked {
  color: #21B74B;
}
.sad-clicked {
  color: #385FC7;
}
.sticker{
  width:50px; 
  height:50px; 
  position:absolute;
  z-index:100;
}
</style>