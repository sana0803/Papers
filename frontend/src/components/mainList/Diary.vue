<template>
    <div>
        <div @click="dialog = true" id="Plus_Item">
            <div id="Plus_Img">
                <v-icon style="font-size:5.0em; color:#FFB319;">add</v-icon>
            </div>
            <div id="Plus_Name">일기장 만들기</div>
        </div>
        <div @click="goDiary" class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">일기장 이름 어쩌구</div>
        </div>
         <!-- Dialog -->
        <v-dialog
            v-model="dialog"
            persistent
            max-width="443"
            >
            <v-card id="Dialog">
                <div id="Dialog_Header">
                    <v-icon @click="dialog = false" id="Dialog_Close" style="font-size:2.0em">close</v-icon>
                </div>
                <div id="Dialog_Content">
                    <div class="Dialog_Header" style="margin-top:20px;">
                        타이틀
                    </div>
                    <div class="Invite_Input">
                        <v-text-field 
                        style="margin-top:10px;"
                        color="#FFB319"
                        label="일기장 타이틀을 입력해주세요."></v-text-field>
                    </div>
                    <div class="Dialog_Header" style="margin-top:5px;">
                        멤버 초대
                    </div>
                    <div class="Invite_Input">
                        <v-text-field 
                        class="Search_Input" 
                        color="#FFB319"
                        label="아이디 검색"></v-text-field>
                        <v-btn id="Search_Btn" icon>
                            <v-icon style="font-size:2.8em;">search</v-icon>
                        </v-btn>
                    </div>
                    <div id="Search_List">
                        <div class="Search_Item">
                            <div class="Search_Img">
                                <img class="Img" src="../../assets/image/user.png">
                            </div>
                            <span class="Search_Name">김싸피</span>
                            <div class="Search_Check"></div>
                        </div>
                        <div class="Search_Item">
                            <div class="Search_Img">
                                <img class="Img" src="../../assets/image/user.png">
                            </div>
                            <span class="Search_Name">김싸피</span>
                            <div class="Search_Check"></div>
                        </div>
                        <div class="Search_Item">
                            <div class="Search_Img">
                                <img class="Img" src="../../assets/image/user.png">
                            </div>
                            <span class="Search_Name">김싸피</span>
                            <div class="Search_Check"></div>
                        </div>
                        <div class="Search_Item">
                            <div class="Search_Img">
                                <img class="Img" src="../../assets/image/user.png">
                            </div>
                            <span class="Search_Name">김싸피</span>
                            <div class="Search_Check"></div>
                        </div>
                        <div class="Search_Item">
                            <div class="Search_Img">
                                <img class="Img" src="../../assets/image/user.png">
                            </div>
                            <span class="Search_Name">김싸피</span>
                            <div class="Search_Check"></div>
                        </div>
                        <div class="Search_Item">
                            <div class="Search_Img">
                                <img class="Img" src="../../assets/image/user.png">
                            </div>
                            <span class="Search_Name">김싸피</span>
                            <div class="Search_Check"></div>
                        </div>
                    </div>
                    <div id="Dialog_Btn_Box">
                        <v-btn style="background:#FFB319; color:white;" class="Dialog_Btn">확인</v-btn>
                        <v-btn @click="dialog=false" style="background:#9F9F9F; color:white;" class="Dialog_Btn">취소</v-btn>
                    </div>  
                </div>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return{
            dialog: false
        }
    },
    methods:{
        goDiary() {
            this.$router.push('/diary')
        }
    }
}
</script>

<style scoped>
#Plus_Item{
    display:inline-block;
    width:284px;
    height:432px;
    margin-bottom:31px;
    margin-left:31px;
}
#Plus_Img{
    width:284px;
    height:394px;
    box-shadow: 2px 3px 12px 2px rgba(35, 35, 35, 0.25);
    cursor: pointer;
    display:flex;
    justify-content: center;
    align-items: center;
}
#Plus_Name{
    margin-top:19px;   
}
.Diary_Item{
    display:inline-block;
    /* border:1px solid red; */
    width:284px;
    height:432px;
    margin-bottom:31px;
    margin-left:31px;
}
.Diary_Img{
    width:284px;
    height:394px;
    background:rgb(176, 247, 135);
    box-shadow: 2px 3px 12px 2px rgba(35, 35, 35, 0.25);
    cursor: pointer;
}
.Diary_Name{
    margin-top:19px;
}
#Dialog{
    height:524px;
}
#Dialog_Header{
    height:36px;
    border-bottom: 1px solid #D7D7D7;
}
#Dialog_Close{
    float:right;
    line-height:36px;
    cursor: pointer;
    margin-right:7px;
}
#Dialog_Content{
    overflow: hidden;
    height:488px;
    padding:0 27px;
}
.Dialog_Header{
    font-size:18px;
    font-weight: bold;
}
.Invite_Input{
    margin-top:0px;
}
.Search_Input{
    width:89%;
    display: inline-block;
}
#Search_Btn{
    background:#FFB319;
    color:white;
    width:40px;
    height:40px;
    position: relative;
    top:-2px;
}
#Search_List{
    height:194px;
    border:1px solid #d7d7d7;
    padding: 14px;
}
#Dialog_Btn_Box{
    width:164px;
    height:32px;
    display:flex;
    justify-content: space-between;
    margin:0 auto;
    margin-top:15px;
}
.Dialog_Btn{
    width:76px;
    height:32px;
}
.Search_Item{
    height:50px;
    line-height: 50px;
}
.Search_Img{
    float:left;
    width:50px;
    height:50px;
    display:flex;
    justify-content: center;
    align-items: center;
}
.Img{
    width:43px;
    height:43px;
    display: inline-block;
}
.Search_Name{
    margin-left:16px;
    font-size:16px;
}
.Search_Check{
    float:right;
    width:18px;
    height:18px;
    border-radius: 20px;
    margin-top:16px;
    margin-right:14px;
    border: 1px solid #D7D7D7;
    cursor: pointer;
}
#Search_List{
  overflow:auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#Search_List::-webkit-scrollbar{
  display: none;
}
</style>