<template>
    <div id="WriteContent_Container">
        <div id="WriteContent_Templete">템플릿자리</div>
        <div id="HashTag_Input">
            <v-text-field  
                label="#여기에 #해시태그를 #입력하세요"
                color="#FFB319"
            ></v-text-field>
        </div>
        <div id="WriteContent_Btn">
            <v-btn
                id="Write_Btn"
                >
                작성
            </v-btn>
            <v-btn
                @click="back"
                id="Back_Btn"
                >
                취소
            </v-btn>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        back() {
            this.$router.go(-1)
        }
    },
};
</script>

<style lang="scss" scoped>
#WriteContent_Container{
    width:530px;
    height:854px;
}
#WriteContent_Templete{
    height:684px;
    box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
}
#HashTag_Input{
    margin-top:30px;
}
#WriteContent_Btn{
    height:38px;
    width:152px;
    margin:0 auto;
    margin-top:30px;
}
#Write_Btn{
    background:#FFB319;
    color:white;
    font-size:17px;
    width:70px;
}
#Back_Btn{
    background: #9F9F9F;
    color:white;
    font-size:17px;
    width:70px;
    margin-left:12px;
}
</style>