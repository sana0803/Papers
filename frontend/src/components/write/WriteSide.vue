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
            <img :src="stickerPackage.stickerList[0].stickerUrl" />
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
              <img :src="stickerList[0].stickerUrl"/>
            </div>
            <div id="Dialog_Name">
              <div id="Name_Author">Papers</div>
              <div id="Name_Name">{{ stickerPackage.stickerPackageName }}</div>
              <div id="Name_Price">{{ stickerPackage.stickerPackagePrice }}장</div>
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
        console.log(this.myFontList)
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
      this.dialog = false
      EventBus.$emit('createSticker', sticker)
    }
  },
  created () {
    this.getUserFonts()
    this.getUserStickers()
    this.$store.commit("setMyFont", {
      fontName: '기본체',
      fontPrice: 0,
      fontUrl: 'Pretendard Variable',
      id: 1,
    })
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
  // background: #eee;
  height: 285px;
  padding: 12px;
  overflow: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#Font_Content::-webkit-scrollbar {
  display: none;
}
.Font_Item {
  width: 317px;
  height: 35px;
  margin-bottom: 3px;
  font-size: 20px;
  position: relative;
  // background: yellow;
}
.Font_Icon {
  position: absolute;
  top: 1px;
}
.Font_Name {
  position: absolute;
  left: 31px;
  cursor: pointer;
  // background: #aaa;
}
.Font_Name:hover {
  // background: #eee;
  color: #ffb319;
  font-weight: 500;
  transition: .2s;
}
#Sticker_Content {
  height: 574px;
  width:85%;
  // background: #ffb319;
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
  height: 185px;
  margin-top: 27px;
  cursor: pointer;
  // background: yellow;
}
.Sticker_Item:hover{
  opacity: 0.7;
}
.Sticker_Img {
  width: 150px;
  height: 150px;
  // background: #eee;
  border: solid 1px #eee;
  overflow: hidden;
  
  img {
    width: 150px;
    // height: 150px;
    object-fit: cover;
  }
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
  // background-color: lightpink;
}
#Dialog_Title {
  height: 118px;
  // background-color: #eee;
  display: flex;
  overflow: hidden;
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
  background: #f7f7f7;
  margin-right: 10px;
  margin-left: 10px;
}
#Dialog_img {
  width: 118px;
  height: 118px;
  border-radius: 100px;
  background: #FAE7CB;

  img {
    margin-top: 11px;
    margin-left: 11px;
    width: 96px;
    height: 96px;
  }
}
#Dialog_Name {
  height: 118px;
  margin-left: 61px;
  // background: lightcoral;
}
#Name_Author {
  margin-top: 16px;
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
  font-weight: 600;
  color: #ffb319;
}
#Name_Btn_Box {
  width: 164px;
  /* height: 32px; */
  display: flex;
  justify-content: space-between;
}
.Name_Btn {
  width: 76px;
  height: 32px;
  box-shadow: none;
}
</style>