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
                @click="write"
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
    computed: {
        loginUser() {
            return this.$store.getters.getLoginUser
        }
    },
    methods: {
        back() {
            this.$router.go(-1)
        },
        write() {
            console.log(this.loginUser.userId)
            const note = {
                designId:1,
                diaryId:1,
                fontId:1,
                layoutId:1,
                noteContent:"테스트입니다",
                noteHashtagList:[
                    "#해시태그",
                    "#테스트",
                    "#입니다"
                ],
                noteMediaList:[
                    "테스트"
                ],
                noteTitle:"테스트제목",
                stickerList:[
                    {
                        leftPixel:1,
                        stickerId:1,
                        topPixel:1
                    }
                ],
                writerId: this.loginUser.userName
            }
            this.$store.dispatch('write',note)
                .then((res) => {
                    console.log(res)
                })
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