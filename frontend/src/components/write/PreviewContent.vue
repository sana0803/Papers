<template>
<div id="preview-back">
  <div id="preview-content-container">
    <div id="preview-templete">
      테스트중
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
  },
  data() {
  },
  methods: {    
    cancel() {
      // this.$router.go(-1);
       this.$emit('onPreview', false)
    },
  }
}
</script>

<style lans="scss" scoped>
#preview-back {
  width: 100%;
  /* height: 100vh; */
  padding-top: 3%;
  /* background-color: #eee; */
}
#preview-content-container {
  margin: 0 auto;
  width: 530px;
  height: 854px;
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