<template>
    <div>
        <div @click="createDialog=true" id="Plus_Item">
            <div id="Plus_Img">
                <v-icon style="font-size:5.0em; color:#FFB319;">add</v-icon>
            </div>
            <div id="Plus_Name">앨범 만들기</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">앨범 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">앨범 이름 어쩌구</div>
        </div>
        <div class="Diary_Item">
            <div class="Diary_Img"></div>
            <div class="Diary_Name">앨범 이름 어쩌구</div>
        </div>
        <!-- delete Dialog -->
        <v-dialog
            v-model="createDialog"
            persistent
            max-width="286"
            >
            <v-card class="Dialog">
                <div class="Dialog_Header">
                    <v-icon @click="dialog=false" class="Dialog_Close" style="font-size:1.5em">close</v-icon>
                </div>
                <div class="Dialog_Content">
                    <div class="Dialog_Text">
                        종이 <span style="color:#ffb319;">150장</span>을 소비해<br>
                        앨범을 만드시겠습니까?
                    </div>
                    <div class="Dialog_Btn_Box">
                        <v-btn style="background:#FFB319; color:white;" class="Dialog_Btn">확인</v-btn>
                        <v-btn @click="createDialog=false" style="background:#9F9F9F; color:white;" class="Dialog_Btn">취소</v-btn>
                    </div>  
                </div>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return {
            createDialog:false
        }
    }
}
</script>

<style scoped>
#Plus_Item{
    display:inline-block;
    width:284px;
    height:353px;
    margin-bottom:31px;
    margin-left:31px;
}
#Plus_Img{
    width:284px;
    height:319px;
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
    width:284px;
    height:353px;
    margin-bottom:31px;
    margin-left:31px;
}
.Diary_Img{
    width:284px;
    height:319px;
    background:rgb(165, 214, 243);
    box-shadow: 2px 3px 12px 2px rgba(35, 35, 35, 0.25);
    cursor: pointer;
}
.Diary_Name{
    margin-top:19px;
}
.Dialog{
    height:180px;
}
.Dialog_Header{
    height:40px;
}
.Dialog_Close{
    float:right;
    margin-top:5px;
    margin-right:5px;
}
.Dialog_Text{
    width:160px;
    height:38px;
    margin:0 auto;
    margin-top:5px;
    font-size:16px;
    text-align: center;
}
.Dialog_Btn_Box{
    width:164px;
    height:32px;
    display:flex;
    justify-content: space-between;
    margin:0 auto;
    margin-top:30px;
}
.Dialog_Btn{
    width:76px;
    height:32px;
}
</style>