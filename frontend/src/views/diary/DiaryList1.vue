<template>
  <div>
    <div id="Line"></div>
    <div
      id="diary-list-item"
      v-for="note in noteList"
      :key="note.noteId"
    >
      <div id="diary-wrap">
        <div class="diary-content">
          <div class="diary-title-wrap">
            <div class="title-sec">
              <span class="diary-title">{{ note.noteTitle }}</span>
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
            <span
              >{{ note.noteContent }}
              </span
            >
          </div>
          <div
            class="diary-hashtag"
            v-for="(hashtag, idx) in note.noteHashtag"
            :key="idx"
          >
            <span>{{ hashtag }}</span>
          </div>          
          <div class="diary-img-wrap">
            <img src="../../assets/image/dog.jpg" alt="일기 사진" />
          </div>
        </div>
        <div class="diary-emotion">
          <div class="emotion-left">
            <div class="emo-item">
              <v-icon @click="clickHeart" class="emo-icon"
                >favorite_border</v-icon
              >
              <div class="emotion-cnt">
                <span>2</span>
              </div>
            </div>
            <div class="emo-item">
              <v-icon @click="clickSmile" class="emo-icon"
                >sentiment_very_satisfied</v-icon
              >
              <div class="emotion-cnt">
                <span>3</span>
              </div>
            </div>
            <div class="emo-item">
              <v-icon @click="clickSad" class="emo-icon"
                >sentiment_dissatisfied</v-icon
              >
              <div class="emotion-cnt">
                <span>0</span>
              </div>
            </div>
          </div>
          <div class="emotion-right"
            v-if="note.writerId == loginUser.userId"
          >
            <span @click="onModifyNote">수정</span>
            <span @click="onDeleteNote">삭제</span>
          </div>
          <div class="emotion-right"
            v-else
          >
          </div>
        </div>
      </div>    
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data() {
    return {
      noteList: [],
      hashtagList: [],
    };
  },
  methods: {
    clickHeart() {},
    clickSmile() {},
    clickSad() {},
    onModifyNote(diary) {
      this.$store.commit('setDiaryContent', diary)
      this.$router.push("/modify");
      // this.$store.dispatch("modifyNote", this.currentDiary.id)
      // .then((res) => {
      //   console.log(res.data)
      // })
    },
    onDeleteNote() {
      this.$store.dispatch("deleteNote", this.currentDiary.id)
      .then(() => {
        console.log('일기 삭제')
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
    ])
  },
  created() {
    console.log(this.currentDiary);
    this.$store.dispatch("getDiaryContent", this.currentDiary.id)
      .then((res) => {
        console.log(res.data)
        this.noteList = res.data.reverse();
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
// .title-sec {
//   background-color: #bbb;
// }
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
  // min-height: 300px;
  overflow: hidden;
  background-color: #bbb;
  img {
    max-width: 100%;
    height: 100%;
  }
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
</style>