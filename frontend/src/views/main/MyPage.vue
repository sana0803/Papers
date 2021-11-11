<template>
  <div id="MyPage_Container">
    <div id="Mypage_Content">
      <div id="Mypage_Title">기본 프로필 편집</div>
      <div id="Mypage_Profile">
        <img :src="profile.profileImg" id="Profile_img" />
        <v-btn @click="imgLabelClick" id="Change_Btn" text> 프로필 사진 바꾸기 </v-btn>
        <input ref="imageInput" type="file" style="display: none; opacity: 0;" @change="onChangeImages" accept=".jpg, .jpeg, .png, .gif" id="profile_img_upload">
      </div>
      <div class="Mypage_Form">
        <span class="Form_Name">닉네임</span>
        <v-text-field
          style="margin-left: 84px"
          class="Form_Input"
          color="#FFB319"
          v-model="profile.nickName"
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
          v-model="profile.pwd"
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
          v-model="profile.pwdCheck"
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
        <v-tab-item class="Tab"> 스티커 목록: {{ myStickerLsit }} </v-tab-item>
        <v-tab-item class="Tab"> 폰트 목록: {{ myFontList }} </v-tab-item>
        <v-tab-item class="Tab"> 커버 목록: {{ myCoverList }} </v-tab-item>
      </v-tabs>
      <div id="Mypage_Delete">
        <v-btn id="Delete_Btn" text> 회원 탈퇴 </v-btn>
      </div>
      <div id="Mypage_Btn_Box">
        <v-btn @click="modify" class="Mypage_Btn" color="#FFB319" style="color: white"
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
import Swal from 'sweetalert2';
import { mapState } from 'vuex';
export default {
  data() {
    return {
      profile: {
        nickName: '',
        pwd: '',
        pwdCheck: '',
        profileImg: '',
        img: ''
      },
      myStickerLsit: [],
      myCoverList: [],
      myFontList: [],
      // profile.nickName : loginUser.nickName,
      // pwd: "123123",
      // pwdCheck: "123123",
      rules: [(value) => !!value || "입력창을 확인해주세요."],
      pwdCheckRule: [
        (value) => !!value || "입력창을 확인해주세요.",
        (value) => {
          if (this.profile.pwd != value) {
            return "비밀번호가 일치하지 않습니다.";
          } else {
            return true;
          }
        },
      ],
    };
  },
  computed: {
    loginUser() {
      return this.$store.getters.getLoginUser;
    },
    ...mapState([
      'loginUser'
    ])
  },
  created: function () {
    this.getProfile()
    this.getUserFonts()
    this.getUserStickers()
    this.getUserCovers()
  },
  onMounted: function () {
    // this.profile.nickName = this.loginUser.userNickname;
    // this.profile.pwd = this.loginUser.userPwd;
    // this.profile.pwdCheck = this.loginUser.userPwd;
    // this.profile.profileImg = this.loginUser.userProfile;
    // console.log(this.profile, '머여,,,');
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    imgLabelClick () {
      const inputImg = document.getElementById('profile_img_upload') // input file 태그 저장
      console.log(inputImg)
      inputImg.click() // 클릭이벤트 실행
    },
    // 사진업로드
    onClickImageUpload () {
      this.$refs.imageInput.click()
    },
    onChangeImages (e) {
      console.log(e.target.files)
      console.log(e.target)
      const file = e.target.files[0] // Get first index in files
      this.profile.profileImg = URL.createObjectURL(file)
      this.profile.image = file // Create File URL
    },
    modify () {
      const newProfile = new FormData()
      newProfile.append('userNickname', this.profile.nickName)
      newProfile.append('userPwd', this.profile.pwd)
      newProfile.append('userProfile', this.profile.image)
      this.$store.dispatch("modifyProfile", newProfile)
      .then(() => {
        Swal.fire({
          icon: "success",
            title:
              '<span style="font-size:25px;">수정되었습니다.</span>',
            confirmButtonColor: "#b0da9b",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
        })
        const user = {
          userNickname: this.profile.nickName,
          userProfile: this.profile.profileImg,
          userPwd : this.profile.pwd,
          userId: this.loginUser.userId,
          userMileage: this.loginUser.userMileage,
          userToken: this.loginUser.userToken
        }
        this.$store.commit("setLoginUser", user)
        console.log(user, '정보수정후 유저')
      })
      .catch(() => {
        Swal.fire({
            icon: "error",
            title: '<span style="font-size:25px;">수정실패.</span>',
            confirmButtonColor: "#f27474",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
          });
      })
    },
    getProfile: function () {
      this.profile.nickName = this.loginUser.userNickname
      this.profile.pwd = this.loginUser.userPwd
      this.profile.pwdCheck = this.loginUser.userPwd
      this.profile.profileImg = this.loginUser.userProfile
    },
    getUserFonts: function () {
      this.$store.dispatch("getUserFonts")
      .then((res) => {
        console.log(res.data, '유저 폰트')
        this.myFontList = res.data
      })
    },
    getUserStickers: function () {
      this.$store.dispatch("getUserStickers")
      .then((res) => {
        console.log(res.data, '유저 스티커')
        this.myStickerLsit = res.data
      })
    },
    getUserCovers: function () {
      this.$store.dispatch("getUserCovers")
      .then((res) => {
        console.log(res.data, '유저 커버')
        this.myCoverList = res.data
      })
    }
  }
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
  border-radius: 50px;
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