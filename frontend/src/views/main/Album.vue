<template>
  <div id="Album_Out">
    <div id="Album_Container">
      <div id="Album_Head">
        <span id="Head_Main">분류</span>
        <div
          @click="select('all')"
          id="list"
          style="color: black; font-weight: bold"
          class="Head_Item"
        >
          전체
        </div>
      </div>
      <div id="Album_Content">
        <div v-if="albumMode == 'all'">
          <All />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import All from "../../components/album/AlbumAll.vue";

export default {
  data() {
    return {
      albumMode: "all",
    };
  },
  components: {
    All,
  },
  methods: {
    select(target) {
      this.albumMode = target;
      var tar = document.getElementById(target);

      var arr = [document.getElementById("all")];

      for (let i = 0; i < 1; i++) {
        if (tar == arr[i]) {
          arr[i].style.color = "black";
          arr[i].style.fontWeight = "bold";
        } else {
          arr[i].style.color = "gray";
          arr[i].style.fontWeight = "400";
        }
      }
    },
  },
};
</script>

<style scoped>
#Album_Out {
  width: 1272px;
  position: relative;
}
#Album_Container {
  margin: 0 auto;
  width: 985px;
}
#Album_Head {
  padding-top: 44px;
  height: 72px;
  margin-left: 12px;
}
#Head_Main {
  color: #ffb319;
  font-size: 20px;
  margin-left: 18px;
}
.Head_Item {
  display: inline-block;
  font-size: 18px;
  margin-left: 36px;
  cursor: pointer;
  color: gray;
}
#Album_Content {
  margin-top: 34px;
  padding-top: 10px;
}
</style>