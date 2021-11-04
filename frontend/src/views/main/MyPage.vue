<template>
  <div id="MyPage_Container">
    <div id="Mypage_Content">
      <div id="Mypage_Title">기본 프로필 편집</div>
      <div id="Mypage_Profile">
        <img :src="loginUser.userProfile" id="Profile_img" />
        <v-btn id="Change_Btn" text> 프로필 사진 바꾸기 </v-btn>
      </div>
      <div class="Mypage_Form">
        <span class="Form_Name">닉네임</span>
        <v-text-field
          style="margin-left: 84px"
          class="Form_Input"
          color="#FFB319"
          v-model="nickName"
          :rules="rules"
        ></v-text-field>
      </div>
      <div class="Mypage_Form">
        <span class="Form_Name">비밀번호</span>
        <v-text-field
          style="margin-left: 70px"
          class="Form_Input"
          type="password"
          color="#FFB319"
          v-model="pwd"
          :rules="rules"
        ></v-text-field>
      </div>
      <div class="Mypage_Form">
        <span class="Form_Name">비밀번호 확인</span>
        <v-text-field
          style="margin-left: 39px"
          class="Form_Input"
          type="password"
          color="#FFB319"
          v-model="pwdCheck"
          :rules="pwdCheckRule"
        ></v-text-field>
      </div>
      <div id="Mypage_Title2">내 구매 목록</div>
      <v-tabs
        fixed-tabs
        style="border: 1px solid #d7d7d7; margin-top: 10px; height: 375px"
        color="#FFB319"
      >
        <v-tab> 스티커 </v-tab>
        <v-tab> 폰트 </v-tab>
        <v-tab> 커버 </v-tab>
        <v-tab-item class="Tab"> 스티커 목록 </v-tab-item>
        <v-tab-item class="Tab"> 폰트 목록 </v-tab-item>
        <v-tab-item class="Tab"> 커버 목록 </v-tab-item>
      </v-tabs>
      <div id="Mypage_Delete">
        <v-btn id="Delete_Btn" text> 회원 탈퇴 </v-btn>
      </div>
      <div id="Mypage_Btn_Box">
        <v-btn class="Mypage_Btn" color="#FFB319" style="color: white"
          >수정</v-btn
        >
        <v-btn
          @click="back"
          class="Mypage_Btn"
          color="#9F9F9F"
          style="color: white"
          >취소</v-btn
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    loginUser() {
      return this.$store.getters.getLoginUser;
    },
  },
  data() {
    return {
      nickName: this.$store.getters.getLoginUser.userName,
      pwd: "123123",
      pwdCheck: "123123",
      rules: [(value) => !!value || "입력창을 확인해주세요."],
      pwdCheckRule: [
        (value) => !!value || "입력창을 확인해주세요.",
        (value) => {
          if (this.pwd != value) {
            return "비밀번호가 일치하지 않습니다.";
          } else {
            return true;
          }
        },
      ],
    };
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
#MyPage_Container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  margin-top: 34px;
  width: 600px;
  height: 800px;
  border-radius: 15px;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
}
#Mypage_Content {
  width: 528px;
  height: 728px;
}
#Mypage_Title {
  font-size: 18px;
  font-weight: 600;
}
#Mypage_Profile {
  height: 45px;
  margin-top: 20px;
}
#Profile_img {
  width: 45px;
  height: 45px;
}
#Change_Btn {
  width: 130px;
  font-size: 15px;
  margin-left: 80px;
  position: relative;
  top: -19px;
}
.Mypage_Form {
  height: 45px;
  /* border:1px solid red; */
}
.Form_Name {
  font-size: 16px;
}
.Form_Input {
  display: inline-block;
  width: 400px;
  position: relative;
  top: -8px;
}
#Mypage_Title2 {
  font-size: 18px;
  font-weight: 600;
  margin-top: 24px;
}
.Tab {
  height: 325px;
  border-top: 1px solid #d7d7d7;
}
#Mypage_Btn_Box {
  margin-top: 20px;
  height: 30px;
  float: right;
}
.Mypage_Btn {
  margin-left: 10px;
}
#Mypage_Delete {
  float: left;
  height: 30px;
  margin-top: 20px;
}
#Delete_Btn {
  color: #9f9f9f;
  width: 50px;
  font-size: 15px;
  position: relative;
  left: -1px;
}
</style>