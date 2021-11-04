<template>
    <div id="Intro_Container">
        <div id="Login_Container">
            <div class="page" id="page1">
                <div v-if="introMode" id="Login_Outer">
                    <div id="Intro_Title">
                        PAPERS
                    </div>
                    <div id="Intro_content">
                        당신의 소중한 추억을 위한<br>
                        일상 기록 서비스
                        
                    </div>
                    <v-btn
                    id="Spread_Btn"
                    @click="spread"
                    text
                    >
                    Click&nbsp;&nbsp; >
                    </v-btn>
                    
                <br>    
                <v-btn
                  id="connection"
                  @click="connection"
                >
                연결
                </v-btn>
                <br>    
                <v-btn
                  id="publish"
                  @click="publish"
                >
                알림 발생
                </v-btn>
                <br>    
                <v-btn
                  id="close"
                  @click="close"
                >
                연결 끊기
                </v-btn>

                </div>
                <div v-if="!introMode" id="Login_left">
                    <div id="Line"></div>
                    <div id="Left_Title">
                        PAPERS
                    </div>
                    <div id="Left_content">
                        당신의 소중한 추억을 위한<br>
                        일상 기록 서비스
                    </div>
                </div>
            </div>
            <div class="page" id="page2">
                <div>
                    <v-text-field 
                    v-on:keyup.enter="login"
                    class="Login_Input" 
                    label="ID"
                    color="#FFB319"
                    v-model="userId"
                    :rules="rules"></v-text-field>
                </div>
                <div>
                    <v-text-field 
                    v-on:keyup.enter="login"
                    class="Login_Input" 
                    type="password" 
                    label="PW"
                    color="#FFB319"
                    v-model="userPwd"
                    :rules="rules"></v-text-field>
                </div>
                <v-btn
                @click="login"
                id="Login_Btn"
                >로그인</v-btn>
                <v-btn
                    id="GoSignUp_Btn"
                    @click="goSignUp"
                    text
                >
                처음이신가요?&nbsp;&nbsp; >
                </v-btn>
            </div>
        </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2'
import { mapGetters } from 'vuex'
export default {
    data() {
        return{
            API_NOTIFICATION_URL: 'https://localhost/api',
            alarmEventSource: null,
            introMode: true,
            userId: '',
            userPwd:'',
            rules: [
                value => !!value || '입력창을 확인해주세요.',
            ]
        }
    },
    computed: {
      ...mapGetters(["getAlarmEventSource"]),
    },
    methods:{
        login() {
            const user = {
                userId: this.userId,
                userPwd: this.userPwd
            }
            this.$store.dispatch('login', user)
                .then((result) => {
                    const loginUser = {
                        userId: result.data.userId,
                        userName: result.data.userName,
                        userMileage: result.data.userMileage,
                        userProfile: result.data.userProfile,
                        userToken: result.data.userToken
                    }
                    // Store에 loginUser 정보 저장
                    this.$store.commit('setLoginUser', loginUser)

                    const alarmEventSource = new EventSource(this.API_NOTIFICATION_URL + `/notification/subscribe?uuid=${loginUser.userToken}`);
                    // 알림 발생 시 이벤트 처리
                    alarmEventSource.onmessage = (e) => {
                      alert(e.data)
                    }
                    this.$store.commit('setAlarmEventSource', alarmEventSource)



                    this.$router.push('main')
                })
                .catch(()=>{
                    Swal.fire({
                        icon: 'error',
                        title: '<span style="font-size:25px;">아이디 또는 비밀번호를 확인해주세요</span>',
                        confirmButtonColor: '#f27474',
                        confirmButtonText: '<span style="font-size:18px;">확인</span>'
                    })
                })
        },
        goSignUp() {
            this.$router.push('signUp')
        },
        connection() {
          // alert('tes')
          const API_NOTIFICATION_URL = 'https://localhost/api'
          // let url = API_NOTIFICATION_URL + "/notification/push?uuid=dsasasa";
          // let eventSource = new EventSource(url, {withCredentials: true});
          // eventSource.onmessage = function (e) {
          //   alert(e.data)
          // }
          // console.log(eventSource)

          const alarmEventSource = new EventSource(API_NOTIFICATION_URL + `/notification/subscribe?uuid=${Math.random()}`);
          this.alarmEventSource = alarmEventSource
          alarmEventSource.onopen = (e) => {
            console.log('---open---')
            console.log(e)
            console.log('----------')
          }
          alarmEventSource.onerror = (e) => {
            console.log('---error---')
            console.log(e)
            console.log('----------')
          }
          alarmEventSource.onmessage = (e) => {
            alert(e.data);
          }
        },
        close () {
          this.getAlarmEventSource.close()
        },
        publish() {
          const API_NOTIFICATION_URL = 'https://localhost/api'
          fetch(API_NOTIFICATION_URL + `/notification/publish?message=알림 발생입니다.`);
        },
        spread() {
            setTimeout(() => this.introMode = false, 250);
            const page1 = document.getElementById('page1')
            const container = document.getElementById('Login_Container')
            page1.style.transform = "rotateY(-180deg)"
            page1.style.transition = "1s"
            container.style.transform = "translate(230px,0px)"
            container.style.transition = "1s"
        }
    }
}
</script>

<style scoped>
#Intro_Container{
    display:flex;   
    border-right:1px solid lightgrey;
    border-left:1px solid lightgrey;
    margin:0 auto;
    width:1280px;
    height:100vh;
    justify-content: center;
    align-items: center;
}
#Login_Container {
    position: relative;
    width: 461px;
    height: 644px;
    perspective: 1500px;
}
#Login_Outer{
    width:461px;
    height:644px;
    margin-left:-33px;
    background:#FAE7CB;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    box-shadow: 2px 3px 12px 2px rgba(35, 35, 35, 0.25);
    border-top-right-radius: 20px;
    border-bottom-right-radius: 20px;
}
#Intro_Title{
    width:190px;
    height:70px;
    line-height:70px;
    text-align: center;
    font-size:62px;
    color:#FFB319;
    font-family: Cafe24Syongsyong;
}
#Intro_content{
    font-size:20px;
    text-align: center;
    color:#585858;
    margin-top:150px;
}
#Spread_Btn{
    color:#222222;
    margin-top:30px;
    font-size:18px;
}
#Login_left{
    width:461px;
    height:644px;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    transform: scaleX(-1);
    margin-left:-33px;
    background: #FAE7CB;
    position:relative;
}
#Left_Title{
    width:190px;
    height:70px;
    line-height:70px;
    text-align: center;
    font-size:60px;
    color:black;
    font-family: Cafe24Syongsyong;
}
#Left_content{
    font-size:20px;
    text-align: center;
    color:black;
    margin-top:150px;
}
#Login_Btn{
    width:200px;
    background: #FFB319;
    color:white;
    position:relative;
    font-size:20px;
    top:30px;
}
#GoSignUp_Btn{
    width:200px;
    position:relative;
    top:50px;
    font-size:18px;
    color:black;
}
.Login_Input{
    width:200px;
    font-size:25px;
}

/* page animation */
.page {
  position: absolute;
  top: 0;
  left: 0;
  padding: 0 32px;
  background-color: #FAE7CB;
  width: 100%;
  height: 100%;
}
#page1 {
  z-index: 1;
  transform-origin: left center;
  transition-duration: 1s;
  transition-timing-function: ease-in;
}
#page2 {
  z-index: 0;
  display:flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
#Line{
    height:590px;
    border: 1px solid #D7D7D7;
    position:absolute;
    top:10px;
    left:461px;
}
</style>