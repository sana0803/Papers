<template>
  <div id="Diary_Container">
    <!-- Header -->
    <Header></Header>
    <div style="height: 7.5vh" />
    <div id="Diary_Header">
      <span style="font-size: 18px">{{currentDiary.diaryTitle}}</span>
      <!-- <span class="Header_txt">김싸피 </span> -->
      <span
        v-if="currentDiary.guest.length >= 0"
        class="guest-txt">
        ({{currentDiary.guest.length + 1}}명)
      </span>
      <span v-else></span>
    </div>
    <div id="Diary_Content">
      <div @click="goList" id="Diary_PostIt1">일기</div>
      <div @click="goManage" id="Diary_PostIt2">관리</div>
      <v-icon @click="change" id="Diary_Btn" style="font-size: 2em"
        >widgets</v-icon
      >
      <div id="Diary_Out">
        <div id="Diary_In">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header.vue";
export default {
  components: {
    Header,
  },
  computed: {
    currentDiary() {
      return this.$store.getters.getCurrentDiary;
    },
  },
  data() {
    return {
      mode: false,
    };
  },
  methods: {
    change() {
      if (!this.mode) {
        this.$router.push("diaryList2");
        this.mode = true;
      } else if (this.mode) {
        this.$router.push("diaryList1");
        this.mode = false;
      }
    },
    goList() {
      this.$router.push("diaryList1");
    },
    goManage() {
      this.$router.push("manage");
    },
  },
};
</script>

<style scoped>
#Diary_Container {
  border-right: 1px solid lightgrey;
  border-left: 1px solid lightgrey;
  margin: 0 auto;
  width: 1272px;
  height: 100vh;
}
#Diary_Header {
  margin-left: 175px;
  margin-top: 45px;
}
.guest-txt {
  font-size: 16px;
  color: #929292;
  margin-left: 8px;
}
#Diary_Content {
  width: 922px;
  height: 687px;
  border: 1px solid white;
  margin: 0 auto;
  margin-top: 45px;
  position: relative;
}
#Diary_PostIt1 {
  display: inline-block;
  width: 28px;
  height: 60px;
  background: #ffb385;
  position: absolute;
  left: 66px;
  line-height: 60px;
  color: white;
  font-size: 14px;
  text-align: center;
  cursor: pointer;
}
#Diary_PostIt2 {
  display: inline-block;
  width: 28px;
  height: 60px;
  background: #b8dfd8;
  position: absolute;
  left: 107px;
  line-height: 60px;
  font-size: 14px;
  text-align: center;
  cursor: pointer;
}
#Diary_Btn {
  position: absolute;
  top: 8px;
  left: 877px;
  cursor: pointer;
}
#Diary_Out {
  width: 922px;
  height: 644px;
  margin-top: 43px;
  background: #fae7cb;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
#Diary_In {
  width: 886px;
  height: 610px;
  background: #fff;
}
</style>