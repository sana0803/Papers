<template>
  <div id="WriteSide_Container">
    <div id="WriteSide_Top">
      <div class="Line"></div>
      <div class="Head_Title">
        <span class="Head_Name">내 폰트</span>
        <v-btn @click="goStore" class="goStore_Btn" color="#FFB319" text>
          상점 보러가기 >
        </v-btn>
      </div>
      <div id="Font_Content">
        <!-- <div class="Font_Item">
          <v-icon class="Font_Icon" style="font-size: 1.2em; color: #ffb319"
            >done</v-icon
          >
          <span class="Font_Name">카페숑24 숑숑체</span>
        </div> -->
        <div v-for="myFont in myFontList" :key="myFont.id" class="Font_Item">
          <v-icon v-if="myFont.fontName == select" class="Font_Icon" style="font-size: 1.2em; color: #ffb319"
            >done</v-icon
          >
          <span class="Font_Name" :style="{ 'font-family': myFont.fontUrl }" @click="selectFont(myFont)">{{myFont.fontName}}</span>
        </div>
      </div>
    </div>
    <div id="WriteSide_Bot">
      <div class="Line"></div>
      <div class="Head_Title">
        <span class="Head_Name">내 스티커</span>
        <v-btn @click="goStore" class="goStore_Btn" color="#FFB319" text>
          상점 보러가기 >
        </v-btn>
      </div>
      <div id="Sticker_Content">
        <div v-for="stickerPackage in stickerPackageList" :key="stickerPackage.id" @click="dialogOn(stickerPackage)" class="Sticker_Item">
          <div class="Sticker_Img">
            <img :src="stickerPackage.stickerList[0].stickerUrl" style="width:100%; height:100%;" />
          </div>
          <div class="Sticker_Name">{{ stickerPackage.stickerPackageName }}</div>
        </div>
      </div>
      <!-- Dialog -->
    <v-dialog v-model="dialog" persistent max-width="500">
      <v-card id="Dialog">
        <div id="Dialog_Header">
          <v-icon
            @click="dialog = false"
            id="Dialog_Close"
            style="font-size: 2em"
            >close</v-icon
          >
        </div>
        <div id="Dialog_Content">
          <div id="Dialog_Title">
            <div id="Dialog_img">
              <img :src="stickerList[0].stickerUrl" style="width: 80%; height: 80%; margin-top: 15px; margin-left: 15px;"/>
            </div>
            <div id="Dialog_Name">
              <div id="Name_Author">작가명 아무개</div>
              <div id="Name_Name">{{ stickerPackage.stickerPackageName }}</div>
              <div id="Name_Price">{{ stickerPackage.stickerPackagePrice }}장</div>
              <!-- <div id="Name_Btn_Box">
                <v-btn
                  style="background: #ffb319; color: white"
                  class="Name_Btn"
                  @click="purchageStickerPackage(stickerPackage)"
                  >구매</v-btn
                >
                <v-btn
                  @click="dialog = false"
                  style="background: #9f9f9f; color: white"
                  class="Name_Btn"
                  >취소</v-btn
                >
              </div> -->
            </div>
          </div>
          <div id="Dialog_List">
            <div class="Dialog_Item" @click="selectSticker(sticker)" v-for="sticker in stickerList" :key="sticker.id">
              <img :src="sticker.stickerUrl" style="width: 80%; height: 80%; margin-top: 10px; margin-left: 10px;"/>
            </div>
          </div>
        </div>
      </v-card>
    </v-dialog>
    </div>
  </div>
</template>

<script>
import EventBus from '../../eventBus'

export default {
  data () {
    return {
      dialog:false,
      select: '기본체',
      myFontList: [],
      stickerPackageList:[],
      stickerList: [{
        id: 1,
        stickerUrl: '',
      }],
      stickerPackage: '',
    }
  },
  methods: {
    dialogOn(stickerPackage) {
      this.stickerPackage = stickerPackage;
      this.stickerList = stickerPackage.stickerList;
      this.dialog = true;
    },
    goStore() {
      this.$router.push("/main/store").catch(() => {});
    },
    getUserFonts: function () {
      this.$store.dispatch("getUserFonts")
      .then((res) => {
        this.myFontList = res.data
      })
    },
    getUserStickers: function () {
      this.$store.dispatch("getUserStickers")
      .then((res) => {
        this.stickerPackageList = res.data
      })
    },
    selectFont: function (font) {
      console.log('폰트 선택함', font)
      this.select = font.fontName
      this.$store.commit("setMyFont", font)
    },
    selectSticker(sticker) {
      EventBus.$emit('createSticker', sticker)
      this.dialog = false
    }
  },
  created () {
    this.getUserFonts()
    this.getUserStickers()
  }

};
</script>

<style lang="scss" scoped>
#WriteSide_Container {
  width: 392px;
  height: 100%;
  border-right: 1px solid #D7D7D7;
  float: left;
}
#WriteSide_Top {
  height: 340px;
}
#WriteSide_Bot {
  height: 628px;
}
.Line {
  height: 5px;
  background: #ffb319;
}
.Head_Title {
  height: 50px;
  border-bottom: 1px solid #D7D7D7;
  line-height: 50px;
  display: flex;
  align-items: center;
}
.Head_Name {
  margin-left: 36px;
  font-size: 18px;
  font-weight: 600;
}
.goStore_Btn {
  margin-left: auto;
  width: 100px;
  margin-right: 22px;
  color: #ffb319;
}
#Font_Content {
  height: 285px;
  overflow: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#Font_Content::-webkit-scrollbar {
  display: none;
}
.Font_Item {
  width: 317px;
  // padding-left:31px;
  height: 30px;
  font-size: 20px;
  margin-top: 15px;
  position: relative;
}
.Font_Icon {
  position: absolute;
  left: 3px;
  top: 1px;
}
.Font_Name {
  position: absolute;
  left: 31px;
  cursor: pointer;
}
.Font_Name:hover {
  background: rgb(221, 221, 221);
}
#Sticker_Content {
  height: 574px;
  width:85%;
  margin:0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  overflow: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#Sticker_Content::-webkit-scrollbar {
  display: none;
}
.Sticker_Item {
  width: 150px;
  height: 230px;
  margin-top: 27px;
  cursor: pointer;
}
.Sticker_Item:hover{
  opacity: 0.7;
}
.Sticker_Img {
  width: 150px;
  height: 205px;
  background: #ffb319;
}
.Sticker_Name {
  font-size: 16px;
  text-align: center;
  margin: 0 auto;
  margin-top: 9px;
}
#Dialog {
  height: 610px;
}
#Dialog_Header {
  height: 42px;
  border-bottom: 1px solid #e7e7e7;
}
#Dialog_Close {
  float: right;
  line-height: 42px;
  cursor: pointer;
  margin-right: 7px;
}
#Dialog_Content {
  height: 568px;
  padding: 34px 46px;
}
#Dialog_Title {
  height: 118px;
}
#Dialog_List {
  height: 306px;
  margin-top: 55px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}
.Dialog_Item {
  width: 100px;
  height: 100px;
  border-radius: 50px;
  background: #fae7cb;
  margin-right: 10px;
  margin-left: 10px;
  cursor:pointer;
}
#Dialog_img {
  display: inline-block;
  width: 118px;
  height: 118px;
  border-radius: 100px;
  background: #b8dfd8;
}
#Dialog_Name {
  // border:1px solid red;
  padding-top:30px;
  display: inline-block;
  /* width:200px; */
  height: 118px;
  margin-left: 61px;
  overflow: hidden;
}
#Name_Author {
  color: #585858;
  font-size: 14px;
}
#Name_Name {
  font-size: 21px;
  font-weight: 600;
}
#Name_Price {
  position: relative;
  top: -3px;
  font-size: 18px;
  color: #ffb319;
}
#Name_Btn_Box {
  width: 164px;
  height: 32px;
  display: flex;
  justify-content: space-between;
}
.Name_Btn {
  width: 76px;
  height: 32px;
  box-shadow: none;
}
</style>