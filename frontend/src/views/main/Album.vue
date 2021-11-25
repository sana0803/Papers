<template>
  <div id="Album_Out">
    <div id="Album_Container">
      <!-- <div id="Album_Head">
        <span id="Head_Main">분류</span>
        <div
          @click="select('all')"
          id="list"
          style="color: #222; font-weight: 600"
          class="Head_Item"
        >
          전체
        </div>
        <div
          @click="select('make')"
          style="color: #222; font-weight: 600"
          class="Head_Item"
        >
          앨범 만들기
        </div>
      </div> -->
      <div id="Album_Content">
        <!-- <div v-if="albumMode == 'all'">
          <All />
        </div> -->
        <!-- <div v-else-if="albumMode == 'make'"> -->
        <div>
          <Make />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import All from "../../components/album/AlbumAll.vue";
import Make from "../../components/album/AlbumMake.vue"

export default {
  data() {
    return {
      albumMode: "all",
    };
  },
  components: {
    // All,
    Make
  },
  methods: {
    select(target) {
      if (target == 'make') {
        this.albumMode = target;
      }
      if (target == 'all') {
        this.albumMode = target;
        var tar = document.getElementById(target);

        var arr = [document.getElementById("list")];
        console.log(arr)

        for (let i = 0; i < 1; i++) {
          if (tar == arr[i]) {
            arr[i].style.color = "#222";
            arr[i].style.fontWeight = "600";
          } else {
            arr[i].style.color = "#9F9F9F";
            arr[i].style.fontWeight = "400";
          }
        }
      }
    }
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
  width: 986px;
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
  font-weight: 500;
}
.Head_Item {
  display: inline-block;
  font-size: 18px;
  margin-left: 36px;
  cursor: pointer;
  color: #9F9F9F;
}
#Album_Content {
  margin-top: 34px;
  padding-top: 10px;
}
</style>