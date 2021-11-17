<template>
<div id="preview-back">
  <div id="preview-content-container">
    <div id="preview-templete">
      <div      
        id="diary-list-item"
      >
        <div id="diary-wrap">
          <div class="diary-content">
            <div class="diary-title-wrap">
              <div class="title-sec">
                <span class="diary-title" :style="{ 'font-family': noteContent.fontId }">{{ noteContent.noteTitle }}</span>
                <br>
                <span class="diary-writer">{{ noteContent.noteId }}</span>
              </div>
              <div class="date-sec">
                <span class="diary-writer">{{ noteContent.writerNickName}}</span>
                <span class="diary-date">
                  {{ noteContent.noteCreatedDate.slice(2, 4)}}.{{ noteContent.noteCreatedDate.slice(5, 7)}}.{{ noteContent.noteCreatedDate.slice(8, 10)}}
                </span>
              </div>
            </div>
            <div id="horizon-line"></div>
            <div class="diary-text">
              <span :style="{ 'font-family': noteContent.fontId }">{{ noteContent.noteContent }}</span>
            </div>
            <div class="diary-hashtag" v-for="(hashtag, idx) in noteContent.noteHashtagList" :key="idx">
              <span>#{{ hashtag }}</span>
            </div>
            <div class="diary-img-wrap">
              <div v-if="noteContent.noteMediaList.length > 1">
                <v-carousel
                  :show-arrows="true"
                  style="height:100%;">
                  <v-carousel-item
                    v-for="(img, i) in noteContent.noteMediaList"
                    :key="i"
                    :src="img"
                    class="diary-content-img"
                  ></v-carousel-item>
                </v-carousel>
              </div>
              <div v-else-if="noteContent.noteMediaList.length == 0">
              </div>
              <div v-else>
                <div style="position:relative;">
                  <img :src="noteContent.noteMediaList[0]" class="diary-content-img" alt="일기 사진" />
                  <img v-for="sticker in noteContent.noteStickerList" :key="sticker.id" :src="sticker.sticker.stickerUrl" :style="{top:sticker.topPixel, left:sticker.leftPixel}" class="sticker" />
                </div> 
              </div>
            </div>
          </div>
          <div class="diary-emotion">
            <div class="emotion-left">
              <div class="emo-item">
                <v-icon class="emo-icon like-clicked">favorite_border</v-icon>
                <div class="emotion-cnt">
                  <span>0</span>
                </div>
              </div>
              <div class="emo-item">
                <v-icon class="emo-icon funny-clicked">sentiment_very_satisfied</v-icon>
                <div class="emotion-cnt">
                  <span>0</span>
                </div>
              </div>
              <div class="emo-item">
                <v-icon class="emo-icon sad-clicked">sentiment_dissatisfied</v-icon>
                <div class="emotion-cnt">
                  <span>0</span>
                </div>
              </div>
            </div>
            <div class="emotion-right" v-if="noteContent.writerId == loginUser.userId">
              <span>수정</span>
              <span>삭제</span>
            </div>
            <div class="emotion-right" v-else>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- <div id="WriteContent_Btn">
      <v-btn @click="write" id="Write_Btn">작성</v-btn>
      <v-btn @click="$emit('onPreview', false)" id="Back_Btn">뒤로가기</v-btn>
    </div>  -->
  </div>
</div>
</template>

<script>
// import Swal from "sweetalert2";

export default {
  props: {
    onPreview: Boolean,
    note: Object,
  },
  data() {
    return {}
  },
  methods: {    
    cancel() {
      // this.$router.go(-1);
      this.$emit('onPreview', false)
    },
  },
  computed: {
    noteContent() {
      return this.$store.getters.getNoteContent
    }
  },
      // this.$store.dispatch("getDiaryContent", this.note.diaryId).then((res) => {
      // this.$store.commit('setCurrentDiary', res.data)
      // this.$store.commit('setNoteContent', this.note) // mutaion 호출 ('뮤테이션 이름, 매개변수)
      // console.log('프리뷰 노트 가져오기')
      // this.$router.push({name: 'diary', query: {diaryId: '1', noteId: '2'}})
        
    // this.$store.dispatch('getDiaryContent', diary.id)
    //   .then((res) => {
    //     this.$store.commit('setNoteContent', res.data.note[0])
    //     this.$router.push("/diary");
    //   })
}
</script>

<style lans="scss" scoped>
#preview-back {
  width: 100%;
  /* height: 100vh; */
  padding-top: 3%;
  background-color: #eee;
}
#preview-content-container {
  margin: 0 auto;
  width: 530px;
  height: 804px;
  background-color: #fff;
}
#preview-templete {
  position:relative;
  height: 684px;
  padding: 20px;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
}



#WriteContent_Btn {
  height: 38px;
  width: 152px;
  margin: 0 auto;
  margin-top: 30px;
  box-shadow: none;
}
#Write_Btn {
  background: #ffb319;
  color: white;
  font-size: 17px;
  width: 70px;
  box-shadow: none;
}
#Back_Btn {
  background: #9f9f9f;
  color: white;
  font-size: 17px;
  width: 70px;
  margin-left: 12px;
  box-shadow: none;
}
</style>