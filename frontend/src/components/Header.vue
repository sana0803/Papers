<template>
    <div id="Header_container">
        <div @click="goHome" id="Header_Title">PAPERS</div>
        <div id="Header_Album">내 앨범</div>
        <div id="Header_Store">상점</div>
        <div id="Header_Alarm">
            <v-icon @click="goAlert" style="font-size:3.0em">notifications</v-icon>
        </div>
        <div id="Header_Profile">
            <v-icon style="font-size:3.0em">account_circle</v-icon>
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
        }
    }
}
</script>

<style scoped>
#Header_container{
    height:7.5vh;
    background:#F5F5F5;
    display:flex;
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
    line-height:7.5vh;
    float:right;
    cursor: pointer;
}
</style>