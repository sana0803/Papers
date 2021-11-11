<template>
  <div id="Header_Container">
    <div @click="goHome" id="header-title">PAPERS</div>
    <div @click="goDiary" id="header-diary">내 일기</div>
    <div @click="goAlbum" id="header-album">내 앨범</div>
    <div @click="goStore" id="header-store">상점</div>
    
    <div id="Header_Profile">
      <img @click="goMyPage" id="Profile_img" :src="loginUser.userProfile" />
    </div>
    <div id="header-mileage">
      <div>
        <span>{{ loginUser.userNickname }} 님</span>
      </div>
      <div>
        <span>종이</span>
        <span>{{ loginUser.userMileage }}장</span>    
      </div>  
    </div>    
    <div id="Header_Alarm">
      <v-icon @click="goAlert" style="font-size: 2em">notifications</v-icon>
    </div>    
    <div id="logout">
      <span id="logout-btn" @click="logout" text> 로그아웃 </span>
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
        goDiary() {
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
            console.log(this.getAlarmEventSource + '로그아웃 시 event close')
            this.getAlarmEventSource.close()
        }
    }
}
</script>

<style lang="scss" scoped>
#Header_Container {
  width: 1272px;
  height: 7.5vh;
  background: #f6f6f6;
  display: flex;
  position: fixed;
  z-index: 100;
}
#header-title {
  line-height: 7.5vh;
  font-family: "Cafe24Syongsyong";
  color: #ffb319;
  font-size: 55px;
  margin-left: 15px;
  cursor: pointer;
}
#header-diary {
  line-height: 7.5vh;
  font-size: 20px;
  margin-left: 40px;
  cursor: pointer;
}
#header-diary:hover {
  font-weight: 500;
  color: #ffb319;
  transition: .2s;
}
#header-album {
  line-height: 7.5vh;
  font-size: 20px;
  margin-left: 40px;
  cursor: pointer;
}
#header-album:hover {
  font-weight: 500;
  color: #ffb319;
  transition: .2s;
}
#header-store {
  line-height: 7.5vh;
  font-size: 20px;
  margin-left: 40px;
  cursor: pointer;
}
#header-store:hover {
  font-weight: 500;
  color: #ffb319;
  transition: .2s;
}
#Header_Profile {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: auto; 
  margin-right: 14px;
  cursor: pointer;
}
#Profile_img {
  width: 2.5em;
  height: 2.5em;
  border-radius: 2.5em;
  background-color: #fff;
}
#header-mileage {
  display: flex;
  flex-direction: column;
  justify-content: center;
  float: right;
  margin-right: 15px; 
  // background-color: aquamarine;
  div:first-child {
    font-size: 16px;
    display: inline-block;
    font-weight: 600;
    // background-color: #aaa;
    line-height: 1.3;
  }
  div:last-child { 
    line-height: 1.4;     
    // background-color: green;
    span {
      color: #919191;
    }
  }
  div:last-child span:last-child {
    margin-left: 6px;
    color: #ffb319;
    font-weight: 500;
  }
}
#Header_Alarm {
  margin-right: 15px;
  line-height: 7.5vh;
  cursor: pointer;
  // background-color: aquamarine;
}
#logout {
  margin-right: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  // background-color: aquamarine;
}
#logout-btn {
  cursor: pointer;
  width: 50px;
  font-size: 14px;
}
#logout-btn:hover {
  color: #ffb319;
  transition: .2s;
}
</style>