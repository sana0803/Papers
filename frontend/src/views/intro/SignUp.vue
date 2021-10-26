<template>
    <div id="SignUp_Container">
        <div id="SignUp_left">
            <div id="Left_Title">
                PAPERS
            </div>
            <div id="Left_content">
                당신의 소중한 추억을 위한<br>
                일상 기록 서비스
            </div>
        </div>
        <div id="SignUp_right">
            <div>
                <v-text-field 
                class="SignUp_Input" 
                label="ID"
                :rules="rules"></v-text-field>
            </div>
            <div>
                <v-text-field 
                class="SignUp_Input" 
                type="password" 
                label="PW"
                :rules="rules"></v-text-field>
            </div>
            <div>
                <v-text-field 
                class="SignUp_Input" 
                type="password" 
                label="NAME"
                :rules="rules"></v-text-field>
            </div>
            <v-btn
            @click="signUp"
            id="SignUp_Btn"
            >회원가입</v-btn>
        </div>
    </div>
</template>

<script>
export default {
    methods:{
        signUp() {
            this.$router.push('main')
        }
    },
    data() {
        return{
            rules: [
                value => !!value || '입력창을 확인해주세요.',
            ]
        }
    }
}
</script>

<style scoped>
#SignUp_Container{
    width:800px;
    height:580px;
}
#SignUp_left{
    width:400px;
    height:580px;
    float:left;
    border-right:1px solid lightgrey;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
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
#SignUp_right{
    width:400px;
    height:580px;
    float:right;
    display:flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}
#SignUp_Btn{
    background:#FFB319;
    color:white;
    width:200px;
    position:relative;
    top:30px;
    font-size:20px;
}
.SignUp_Input{
    width:200px;
    font-size:25px;
}
</style>