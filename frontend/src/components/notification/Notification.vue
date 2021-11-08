<template>
  <div align="center" class="noti-container">
    <div align="right" class="noti-top">
      <v-icon class="close-noti" style="font-size: 1em">close</v-icon>
    </div>
    <div class="noti-body">
      <div class="left-area">
        <img :src="imageUrl" />
      </div>
      <div align="left" class="right-area">
        김예진님이 '김이박 함께쓰는 일기장'에 일기를 작성했습니다. 
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: 'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/cbdef037365169.573db7853cebb.jpg',
    }
  },
  methods: {
  },
};
</script>

<style scoped>
.left-area {
  float: left;
  width: 30%;
  padding: 25px;
}
.left-area > img {
  width: 50px;
  height: 50px;
  border-radius: 25px;
}
.right-area {
  float: right;
  width: 70%;
  padding: 25px 10px;
  height: 100%;
  line-height: 20px;
}
.noti-container {
  width: 300px;
  height: 160px;
  /* position: fixed; */
  position: absolute;
  left: 83%;
  top: 78%;
  border-radius: 12px;
  background-color: #fff;
  box-shadow: 4px 4px 10px rgba(43, 43, 49, 0.25);
}
.noti-top {
  height: 10%;
  margin: 10px 20px;
}
.noti-body {
  height: 90%;
  line-height: 90%;
}
.close-noti {
  cursor: pointer;
}
.close-noti:hover {
  color: yellow;
}
</style>