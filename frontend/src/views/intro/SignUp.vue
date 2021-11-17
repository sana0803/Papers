<template>
  <div id="SignUp_Container">
    <div id="SignUp_left">
      <img class="Img" src="../../assets/image/paper.png" />
      <div id="Line"></div>
      <div id="Left_Title">PAPERS</div>
      <div id="Left_content">
        당신의 소중한 추억을 위한<br />
        일상 기록 서비스
      </div>
    </div>
    <div id="SignUp_right">
      <div id="Form_Box">
        <div>
          <v-text-field
            class="SignUp_Input"
            label="아이디"
            color="#FFB319"
            v-model="userId"
            :rules="rules"
          ></v-text-field>
        </div>
        <div>
          <v-text-field
            class="SignUp_Input"
            type="password"
            label="비밀번호"
            color="#FFB319"
            v-model="userPwd"
            :rules="rules"
          ></v-text-field>
        </div>
        <div>
          <v-text-field
            class="SignUp_Input"
            type="password"
            label="비밀번호확인"
            color="#FFB319"
            v-model="userPwdCheck"
            :rules="rules"
          ></v-text-field>
        </div>
        <div>
          <v-text-field
            class="SignUp_Input"
            label="닉네임"
            color="#FFB319"
            v-model="userNickname"
            :rules="rules"
          ></v-text-field>
        </div>
        <v-btn @click="signUp" id="SignUp_Btn">회원가입</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { mapActions } from "vuex";
const Login = "Login";

export default {
  data() {
    return {
      rules: [(value) => !!value || "입력창을 확인해주세요."],
      userId: "",
      userPwd: "",
      userPwdCheck: "",
      userNickname: "",
    };
  },
  methods: {
    ...mapActions(Login, ["sign"]),
    signUp() {
      const user = {
        userId: this.userId,
        userPwd: this.userPwd,
        userNickname: this.userNickname,
        // userName: "이름",
      };
      this.$store
        .dispatch("signUp", user)
        .then(() => {
          Swal.fire({
            icon: "success",
            title:
              '<span style="font-size:25px;">회원가입을 축하드립니다.</span>',
            confirmButtonColor: "#b0da9b",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
          });
          this.$router.push("/").catch(() => {});
        })
        .catch(() => {
          Swal.fire({
            icon: "error",
            title: '<span style="font-size:25px;">중복된 아이디입니다.</span>',
            confirmButtonColor: "#f27474",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
          });
        });
    },
  },
};
</script>

<style scoped>
#SignUp_Container {
  background: #fae7cb;
  border-radius: 20px;
  width: 922px;
  height: 644px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 2px 3px 12px 2px rgba(35, 35, 35, 0.25);
}
#SignUp_left {
  width: 443px;
  height: 610px;
  background: white;
  float: left;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: relative;
}
#Left_Title {
  width: 190px;
  height: 70px;
  line-height: 70px;
  text-align: center;
  font-size: 60px;
  color: #ffb319;
  font-family: Cafe24Syongsyong;
}
#Left_content {
  font-size: 20px;
  text-align: center;
  color: #222222;
  margin-top: 80px;
}
#SignUp_right {
  margin-left: 0px;
  width: 445px;
  height: 610px;
  background: white;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
#SignUp_Btn {
  background: #ffb319;
  color: white;
  width: 220px;
  margin-top: 10px;
  position: relative;
  /* top:10px; */
  font-size: 20px;
  box-shadow: none;
}
.SignUp_Input {
  width: 220px;
  font-size: 25px;
  margin-top: -10px;
}
#Line {
  height: 590px;
  border: 1px solid #e7e7e7;
  position: absolute;
  top: 10px;
  left: 443px;
}
#Form_Box {
  height: 273px;
}
.Img {
  width: 87px;
  height: 87px;
}
</style>