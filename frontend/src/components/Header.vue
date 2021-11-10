<template>
  <div id="Header_Container">
    <div @click="goHome" id="Header_Title">PAPERS</div>
    <div @click="goHome" id="Header_Note">일기</div>
    <div @click="goAlbum" id="Header_Album">내 앨범</div>
    <div @click="goStore" id="Header_Store">상점</div>
    <div id="Header_Nickname">{{ loginUser.userNickname }}님</div>
    <div id="Header_Mileage">{{ loginUser.userMileage }}장</div>
    <div id="Header_Alarm">
      <v-icon @click="goAlert" style="font-size: 3em">notifications</v-icon>
    </div>
    <div id="Header_Profile">
      <img @click="goMyPage" id="Profile_img" :src="loginUser.userProfile" />
    </div>
    <div id="Logout">
      <v-btn id="Logout_Btn" @click="logout" text> 로그아웃 </v-btn>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    computed: {
        loginUser() {
            return this.$store.getters.getLoginUser
        },
        ...mapGetters(["getAlarmEventSource"]),
    },
    methods: {
        goHome() {
            this.$router.push('/main')
        },
        goAlbum() {
            this.$router.push('album')
        },
        goStore() {
            this.$router.push('store')
        },
        goAlert() {
            this.$router.push('alert')
        },
        goMyPage() {
            this.$router.push('myPage')
        },
        logout() {
            this.$store.commit('setLoginUser', {})
            this.$router.push('/')
            console.log(this.getAlarmEventSource + '로그 아웃 시 event close')
            this.getAlarmEventSource.close()
        }
    }
}
</script>

<style scoped>
#Header_Container {
  width: 1272px;
  height: 7.5vh;
  background: #f5f5f5;
  display: flex;
  position: fixed;
  z-index: 100;
}
#Header_Title {
  line-height: 7.5vh;
  font-family: Cafe24Syongsyong;
  color: #ffb319;
  font-size: 55px;
  margin-left: 15px;
  cursor: pointer;
}
#Header_Note{
  line-height: 7.5vh;
  font-size: 20px;
  margin-left: 40px;
  cursor: pointer;
}
#Header_Album {
  line-height: 7.5vh;
  font-size: 20px;
  margin-left: 40px;
  cursor: pointer;
}
#Header_Store {
  line-height: 7.5vh;
  font-size: 20px;
  margin-left: 40px;
  cursor: pointer;
}
#Header_Nickname{
  margin-right: 15px;
  margin-left: auto;
  line-height: 7.5vh;
  font-size: 15px;
  font-weight: 500;
}
#Header_Mileage {
  margin-right: 15px;
  line-height: 7.5vh;
  color: #ffb319;
  font-size: 15px;
  font-weight: 500;
}
#Header_Alarm {
  margin-right: 15px;
  line-height: 7.5vh;
  cursor: pointer;
}
#Header_Profile {
  margin-right: 15px;
  line-height: 75px;
  float: right; 
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}
#Profile_img {
  width: 2.8em;
  height: 2.8em;
  border-radius:50px;
}
#Logout {
  margin-right: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
}
#Logout_Btn {
  width: 50px;
  font-size: 14px;
}
</style>