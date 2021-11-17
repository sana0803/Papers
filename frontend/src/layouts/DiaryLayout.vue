<template>
  <div id="Diary_Container">
    <!-- Header -->
    <Header></Header>
    <div style="height: 7.5vh" />
    <div id="Diary_Header">
      <span style="font-size: 18px">{{ currentDiary.diaryTitle }}</span>
      <!-- <span class="Header_txt">김싸피 </span> -->
      <span
        v-if="currentDiary.guest.length >= 0 && currentDiary.ownerId == loginUser.userId"
        class="guest-txt"
      >
        ({{ currentDiary.guest.length + 1 }}명)
      </span>
      <span
        v-if="currentDiary.guest.length >= 0 && currentDiary.ownerId != loginUser.userId"
        class="guest-txt"
      >
        ({{ currentDiary.guest.length + 2 }}명)
      </span>
      <span v-else></span>
    </div>
    <div id="Diary_Content">
      <div @click="goList" id="Diary_PostIt1">일기</div>
      <div v-if="currentDiary.ownerId == loginUser.userId" @click="goManage" id="Diary_PostIt2">
        관리
      </div>
      <v-icon @click="change" id="Diary_Btn" style="font-size: 2em">widgets</v-icon>
      <div id="Diary_Out" class="diaryCover">
        <div id="Diary_In">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header.vue";
import EventBus from "../eventBus";

export default {
  components: {
    Header,
  },
  computed: {
    currentDiary() {
      return this.$store.getters.getCurrentDiary;
    },
    loginUser() {
      return this.$store.getters.getLoginUser;
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
      this.$router.push("diaryList1").catch(() => {});
      const now = document.getElementById("Diary_PostIt1")
      const managebtn = document.getElementById("Diary_PostIt2")
      now.style.width = "45px";
      now.style.fontWeight = "700"
      now.style.color = 'black'
      managebtn.style.fontWeight = "400"
      managebtn.style.color = '#929292'
      managebtn.style.width = "28px"
      managebtn.style.left = "120px"
    },
    goManage() {
      this.$router.push("manage").catch(() => {});
      const now = document.getElementById("Diary_PostIt1")
      const managebtn = document.getElementById("Diary_PostIt2")
      now.style.width = "28px";
      now.style.fontWeight = "400"
      now.style.color = '#929292'
      managebtn.style.left = "107px"
      managebtn.style.fontWeight = "700"
      managebtn.style.width = "45px"
      managebtn.style.color = 'black'

    },
    // 일기장 레이아웃 커버
    setCover() {
      const box = document.getElementById("Diary_Out");
      box.style.backgroundImage = "url("+ this.currentDiary.diaryCover.coverUrl +")"
    },
  },
  mounted() {
    const loginCheck = localStorage.getItem("userId");
    if (loginCheck == null) {
      this.$router.push("/");
    }
    // 일기장 커버선택시 레이아웃 변경
    EventBus.$on("changeCover", (cover) => {
      const box = document.getElementById("Diary_Out");
      box.style.backgroundImage = "url(" + cover.coverUrl + ")";
    });
    this.setCover();
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
  margin-top: 40px;
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
  margin-top: 25px;
  position: relative;
}
#Diary_PostIt1 {
  display: inline-block;
  width: 45px;
  height: 60px;
  background: #ffb385;
  position: absolute;
  left: 66px;
  line-height: 60px;
  font-size: 14px;
  font-weight: 700;
  text-align: center;
  cursor: pointer;
}
#Diary_PostIt2 {
  display: inline-block;
  width: 28px;
  height: 60px;
  background: #b8dfd8;
  position: absolute;
  /* left: 107px; */
  left: 120px;
  line-height: 60px;
  font-size: 14px;
  text-align: center;
  cursor: pointer;
  color: #929292;
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
