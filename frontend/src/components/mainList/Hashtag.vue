<template>
    <div>
        <div id="Hashtag_Search">
            <v-text-field 
            class="Search_Input" 
            color="#FFB319"
            label="해시태그를 검색해보세요."></v-text-field>
            <v-btn id="Search_Btn" icon>
                <v-icon style="font-size:2.8em;">search</v-icon>
            </v-btn>
        </div>
        <div id="Hastag_List">
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
            <div class="Hashtag_Item">#해시태그</div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#Hashtag_Search{
    width:392px;
    margin:0 auto;
}
.Search_Input{
    width:85%;
    display: inline-block;
}
#Search_Btn{
    background:#FFB319;
    color:white;
    width:40px;
    position: relative;
    top:-2px;
}
#Hastag_List{
    text-align: center;
    margin-top:14px;
    height:200px;
}
.Hashtag_Item{
    background:rgb(231, 231, 231);
    border-radius: 15px;
    padding:5px 5px 5px 5px;
    height:35px;
    font-size:18px;
    line-height:23px;
    margin:10px;
    display: inline-block;
    cursor:pointer;
}
.Hashtag_Item:hover{
    background: rgb(212,212,212);
}
</style>