<template>
    <div>
        <div @click="goCreate" id="Plus_Item">
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
    </div>
</template>

<script>
export default {
    methods:{
        goDiary() {
            this.$router.push('/diary')
        },
        goCreate() {
            console.log('create')
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
</style>