<template>
  <div id="Store_Out">
    <div id="Store_Container">
      <div id="Store_Head">
        <span id="Head_Main">분류</span>
        <div
          @click="select('sticker')"
          id="sticker"
          style="color: #222; font-weight: 600"
          class="Head_Item"
        >
          스티커
        </div>
        <div @click="select('font')" id="font" class="Head_Item">폰트</div>
        <div @click="select('cover')" id="cover" class="Head_Item">
          일기장 커버
        </div>
      </div>
      <div id="Store_Content">
        <div v-if="storeMode == 'sticker'">
          <Sticker />
        </div>
        <div v-if="storeMode == 'font'">
          <Font />
        </div>
        <div v-if="storeMode == 'cover'">
          <Cover />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Sticker from "../../components/store/Sticker.vue";
import Font from "../../components/store/Font.vue";
import Cover from "../../components/store/Cover.vue";

export default {
  data() {
    return {
      storeMode: "sticker",
    };
  },
  components: {
    Sticker,
    Font,
    Cover,
  },
  methods: {
    select(target) {
      this.storeMode = target;
      var tar = document.getElementById(target);

      var arr = [
        document.getElementById("sticker"),
        document.getElementById("font"),
        document.getElementById("cover"),
      ];

      for (let i = 0; i < 3; i++) {
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
      this.$router.push("templete").catch(() => {});
    },
  },
};
</script>

<style scoped>
#Store_Out {
  width: 1272px;
  position: relative;
}
#Store_Container {
  margin: 0 auto;
  width: 986px;
}
#Store_Head {
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
#Store_Content {
  margin-top: 34px;
  padding-top: 10px;
}
</style>