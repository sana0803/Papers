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
                </div>
                <div v-if="!introMode" id="Login_left">
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
                    class="Login_Input" 
                    label="ID"
                    :rules="rules"></v-text-field>
                </div>
                <div>
                    <v-text-field 
                    class="Login_Input" 
                    type="password" 
                    label="PW"
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
export default {
    data() {
        return{
            introMode: true,
            rules: [
                value => !!value || '입력창을 확인해주세요.',
            ]
        }
    },
    methods:{
        login() {
            this.$router.push('main')
        },
        goSignUp() {
            this.$router.push('signUp')
        },
        spread() {
            setTimeout(() => this.introMode = false, 250);
            const page1 = document.getElementById('page1')
            const container = document.getElementById('Login_Container')
            page1.style.transform = "rotateY(-180deg)"
            page1.style.transition = "1s"
            container.style.transform = "translate(200px,0px)"
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
    width: 400px;
    height: 580px;
    perspective: 1000px;
}
#Login_Outer{
    width:400px;
    height:580px;
    margin-left:-33px;
    background:#FFB319;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}
#Intro_Title{
    width:190px;
    height:70px;
    line-height:70px;
    text-align: center;
    font-size:60px;
    color:white;
    font-family: Cafe24Syongsyong;
}
#Intro_content{
    font-size:20px;
    text-align: center;
    color:white;
    margin-top:150px;
}
#Spread_Btn{
    color:white;
    margin-top:30px;
    font-size:18px;
}
#Login_left{
    width:400px;
    height:580px;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    transform: scaleX(-1);
    margin-left:-33px;
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
  background-color: #ffffff;
  box-shadow: 0 0 1px lightgrey, -1px 0 1px lightgrey;
  width: 100%;
  height: 100%;
}

#page1 {
  z-index: 1;
  transform-origin: left center;
  transition-duration: 1s;
  transition-timing-function: ease-in;
  border:1px solid lightgrey;
}
#page2 {
  z-index: 0;
  display:flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border:1px solid lightgrey;
}
</style>