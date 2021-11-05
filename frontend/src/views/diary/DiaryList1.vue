<template>
  <div>
    <div id="Line"></div>
    <div id="diary-left-wrap">
      <div class="diary-content">
        <div class="diary-title-wrap">
          <span class="diary-title">dtd {{ diaryList[0].noteTitle }}</span>
          <span class="diary-date">21.11.04</span>
        </div>
        <div class="diary-text">
          <span
            >어쩌구 저쩌구 일기 간략하게 한두 줄만 씁니다. 어쩌구 저쩌구 일기
            간략하게 한두 줄만 씁니다. 어쩌구 저쩌구 일기 간략하게 한두 줄만
            씁니다. 어쩌구 저쩌구 일기 간략하게 한두 줄만 씁니다. 어쩌구 저쩌구
            일기 간략하게 한두 줄만 씁니다.</span
          >
        </div>
        <div class="diary-hashtag">
          <span>#해시태그</span>
        </div>
        <div class="diary-hashtag">
          <span>#강아지</span>
        </div>
        <div class="diary-hashtag">
          <span>#귀여워</span>
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
        <div class="emotion-right">
          <span>수정</span>
          <span>삭제</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      diaryList: [],
      title: '',
      content: '',
    };
  },
  methods: {
    clickHeart() {},
    clickSmile() {},
    clickSad() {},
  },
  computed: {
    currentDiary() {
      return this.$store.getters.getCurrentDiary;
    },
  },
  created() {
    console.log(this.currentDiary);
    this.$store.dispatch("getDiaryContent", this.currentDiary.id)
      .then((res) => {
        console.log(res.data)
        this.diaryList = res.data
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
#diary-left-wrap {
  width: 371px;
  height: 538px;
  margin: 36px 36px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.diary-content {
  max-height: 485px;
  // background-color: green;
  overflow: hidden;
}
.diary-title-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2px;
}
.diary-title {
  font-size: 20px;
  font-weight: 500;
}
.diary-date {
  color: #929292;
  font-size: 14px;
}
.diary-text {
  margin-bottom: 15px;
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
  margin-top: 25px;
  // max-width: 100%;
  max-height: 300px;
  background-color: bisque;
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