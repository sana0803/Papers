<template>
    <div id="WriteSide_Container">
        <div id="WriteSide_Top">
            <div class="Line"></div>
            <div class="Head_Title">
                <span class="Head_Name">내 폰트</span>
                <v-btn
                    @click="goStore"
                    class="goStore_Btn"
                    color="#FFB319"
                    text
                >
                상점 보러가기 >
                </v-btn>
            </div>
            <div id="Font_Content">
                <div class="Font_Item">
                    <v-icon class="Font_Icon" style="font-size:1.2em; color:#FFB319;">done</v-icon>
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
                <div class="Font_Item">
                    <span class="Font_Name">카페숑24 숑숑체</span>
                </div>
            </div>
        </div>
        <div id="WriteSide_Bot">
            <div class="Line"></div>
            <div class="Head_Title">
                <span class="Head_Name">내 스티커</span>
                <v-btn
                    @click="goStore"
                    class="goStore_Btn"
                    color="#FFB319"
                    text
                >
                상점 보러가기 >
                </v-btn>
            </div>
            <div id="Sticker_Content">
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
                <div class="Sticker_Item">
                    <div class="Sticker_Img"></div>
                    <div class="Sticker_Name">초 귀요미 스티커팩</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        goStore() {
            this.$router.push('/main/store')
        }
    },
};
</script>

<style lang="scss" scoped>
#WriteSide_Container{
    width:392px;
    height:100%;
    border-right:1px solid lightgray;
    float:left;
}
#WriteSide_Top{
    height:340px;
}
#WriteSide_Bot{
    height:628px;
}
.Line{
    height:5px;
    background: #FFB319;
}
.Head_Title{
    height:50px;
    border-bottom: 1px solid lightgray;
    line-height:50px;
    display:flex;
    align-items:center;
}
.Head_Name{
    margin-left:36px;
    font-size:18px;
}
.goStore_Btn{
    margin-left:auto;
    width:100px;
    margin-right:22px;
    color:#FFB319;
}
#Font_Content{
    height:285px;
    overflow:auto;
    -ms-overflow-style: none;
    scrollbar-width: none;
}
#Font_Content::-webkit-scrollbar{
  display: none;
}
.Font_Item{
    width:317px;
    // padding-left:31px;
    height:30px;
    font-size:21px;
    margin-top:15px;
    position: relative;
}
.Font_Icon{
    position:absolute;
    left:3px;
    top:1px;
}
.Font_Name{
    position:absolute;
    left:31px;
    cursor: pointer;
}
.Font_Name:hover{
    background: rgb(221, 221, 221);
}
#Sticker_Content{
    height:574px;
    display:flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    overflow:auto;
    -ms-overflow-style: none;
    scrollbar-width: none;
}
#Sticker_Content::-webkit-scrollbar{
  display: none;
}
.Sticker_Item{
    width:150px;
    height:230px;
    margin-top:27px;
}
.Sticker_Img{
    width:150px;
    height:205px;
    background: #FFB319;
}
.Sticker_Name{
    font-size:16px;
    text-align: center;
    margin:0 auto;
    margin-top:9px;
}
</style>