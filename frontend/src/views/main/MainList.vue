<template>
  <div id="MainList_Out">
    <v-btn
      v-if="mainListMode == 'list'"
      id="write-diary-btn"
      @click="goTemplete"
      fab
      dark
      large
      color="#FFB319"
    >
      <v-icon dark>mdi-plus</v-icon>
    </v-btn>
    <!-- <v-btn
            id="connection"
            @click="connection"
          >
          알림음 테스트
          </v-btn> -->
    <div id="MainList_Container">
      <div id="MainList_Head">
        <span id="Head_Main">분류</span>
        <div
          @click="select('list')"
          id="list"
          style="color: #222; font-weight: 600;"
          class="Head_Item"
        >
          전체
        </div>
        <div @click="select('diary')" id="diary" class="Head_Item">일기장</div>
        <div @click="select('calender')" id="calender" class="Head_Item">
          날짜
        </div>
        <div @click="select('hashtag')" id="hashtag" class="Head_Item">
          해시태그
        </div>
      </div>
      <div id="MainList_Content">
        <div v-if="mainListMode == 'list'">
          <All />
        </div>
        <div v-if="mainListMode == 'diary'">
          <Diary />
        </div>
        <div v-if="mainListMode == 'calender'">
          <Calender />
        </div>
        <div v-if="mainListMode == 'hashtag'">
          <Hashtag />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import All from "../../components/mainList/All.vue";
import Diary from "../../components/mainList/Diary.vue";
import Calender from "../../components/mainList/Calender.vue";
import Hashtag from "../../components/mainList/Hashtag.vue";

export default {
  data() {
    return {
      mainListMode: "list",
    };
  },
  components: {
    All,
    Diary,
    Calender,
    Hashtag,
  },
  methods: {
    select(target) {
      this.mainListMode = target;
      var tar = document.getElementById(target);

      var arr = [
        document.getElementById("list"),
        document.getElementById("diary"),
        document.getElementById("calender"),
        document.getElementById("hashtag"),
      ];

      for (let i = 0; i < 4; i++) {
        if (tar == arr[i]) {
          arr[i].style.color = "#222";
          arr[i].style.fontWeight = "600";
        } else {
          arr[i].style.color = "#9F9F9F";
          arr[i].style.fontWeight = "400";
        }
      }
    },
    goTemplete() {
      this.$store.commit('initNoteContent')
      this.$store.commit('setIsUpdate', false)
      this.$router.push("/write");
    },
  },
};
</script>

<style scoped>
#MainList_Out {
  width: 1272px;
  position: relative;
}
#write-diary-btn {
  position: fixed;
  left: 77.2%;
  top: 86.3%;
  box-shadow: none;
}
#MainList_Container {
  margin: 0 auto;
  width: 986px;
}
#MainList_Head {
  padding-top: 44px;
  height: 72px;
  margin-left: 12px;
}
#Head_Main {
  color: #ffb319;
  font-size: 20px;
  margin-left: 18px;
  font-weight: 500;
}
.Head_Item {
  display: inline-block;
  font-size: 18px;
  margin-left: 36px;
  cursor: pointer;
  color: #9F9F9F;
}
#MainList_Content {
  margin-top: 34px;
  padding-top: 10px;
}
</style>