<template>
    <div id="Header_Container">
        <div @click="goHome" id="Header_Title">PAPERS</div>
        <div id="Header_Album">내 앨범</div>
        <div id="Header_Store">상점</div>
        <div id="Header_Alarm">
            <v-icon @click="goAlert" style="font-size:3.0em">notifications</v-icon>
        </div>
        <div id="Header_Profile">
            <img @click="goMyPage" id="Profile_img" src="../assets/image/user.png" />
        </div>
        <div id="Logout">
            <v-btn
                id="Logout_Btn"
                @click="logout"
                text
                >
                로그아웃
                </v-btn>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
        }
    },
    methods: {
        goHome() {
            this.$router.push('/main')
        },
        goAlert() {
            this.$router.push('alert')
        },
        goMyPage() {
            this.$router.push('myPage')
        },
        logout() {
            this.$router.push('/')
        }
    }
}
</script>

<style scoped>
#Header_Container{
    width:1272px;
    height:7.5vh;
    background:#F5F5F5;
    display:flex;
    position:fixed;
    z-index: 100;
}
#Header_Title{
    line-height:7.5vh;
    font-family: Cafe24Syongsyong;
    color: #FFB319;
    font-size:55px;
    margin-left:15px;
    cursor: pointer;
}
#Header_Album{
    line-height:7.5vh;
    font-size:20px;
    margin-left:40px;
    cursor: pointer;
}
#Header_Store{
    line-height:7.5vh;
    font-size:20px;
    margin-left:40px;
    cursor: pointer;
}
#Header_Alarm{
    margin-left: auto;
    margin-right: 15px;
    line-height:7.5vh;
    cursor: pointer;
}
#Header_Profile{
    margin-right:15px;
    line-height:75px;
    float:right;
    cursor: pointer;
    display:flex;
    justify-content: center;
    align-items: center;
}
#Profile_img{
    width:2.8em;
    height:2.8em;
}
#Logout{
    margin-right: 15px;
    display: flex;
    justify-content: center;
    align-items: center;
}
#Logout_Btn{
    width:50px;
    font-size:14px;
}
</style>