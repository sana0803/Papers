<template>
  <div style="text-align: center; padding: 10px">
    <!-- 사이드 메뉴 -->
    <div id="albumMenu" data-html2canvas-ignore="true">
      <v-btn
        @click="appendImage"
        outlined
        color="#FFB319"
        style="margin-right: 10px; box-shadow: none;"
      >
        사진 추가하기
      </v-btn>
      <v-btn
        @click="appendArea"
        outlined
        color="#FFB319"
        style="box-shadow: none;"
      >
        페이지 추가하기
      </v-btn>
      <v-btn
        @click="exportToPDF"
        color="#FFB319"
        dark
        style="display: block; width: 100%; margin-top: 10px; box-shadow: none;"
        >pdf 만들기</v-btn
      >
      <div style="background: #fff; margin-top: 36px;">
        <p style="font-weight: 600; text-align: left; margin-bottom: 10px;">내 폰트</p>
        <hr>
        <v-list-item-group id="fontList">
          <v-list-item v-for="(myFont, i) in myFontList" :key="i">
            <v-list-item-content
              @click="selectFont(myFont)"
              :style="{ 'font-family': myFont.fontUrl }"
            >
              <v-list-item-title :style="{ 'font-family': myFont.fontUrl }">{{
                myFont.fontName
              }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </div>
    </div>

    <!-- 콘텐츠 -->
    <div id="album-contents-wrap" ref="pdfarea">
      <div class="albumContent" style="height: 100%; overflow: auto">
        <div class="picContents" v-for="(image, idx) in imageList" :key="idx">
          <div class="picContentsheader" data-html2canvas-ignore="true">
            이곳을 눌러 움직여보세요.
          </div>
          <div class="pic-content-wrap">
            <img
              :src="image"
              @click="dialogOn(idx)"
              style="width: 100%; margin-bottom: 12px; cursor:pointer;"
            />
            <textarea
              name="content"
              class="img-desc"
              rows="1"
              cols="40"
              color="#ffffff"
              hide-details
              placeholder="내용을 입력하세요"
              :style="{ 'font-family': getMyFont.fontUrl }"
            >
            </textarea>
          </div>
        </div>
      </div>
    </div>

    <!-- 다이얼로그 -->
    <v-dialog
      id="getImageForm"
      v-model="dialog"
      persistent
      max-width="750"
    >
      <v-card>
        <div id="album-dialog-header">
          <v-icon
            @click="dialog = false"
            id="album-dialog-close"
            style="font-size: 2em"
            >close</v-icon
          >
        </div>
        <!-- 사진 가져오기 -->
        <div class="dialog-wrap">
          <div>
            <v-select
              v-model="selectDiary"
              :items="diaryTitleList"
              label="일기장 선택"
              style="width: 45%;"
              color="#FFB319"            
            ></v-select>
            <v-btn
              @click="getS3ImageList"
              class="get-img-btn"
              color="#FFB319"
              style="color: #fff; font-size: 17px;"
              >이미지 가져오기</v-btn
            >
          </div>
          <div class="s3-img-wrap">
            <div
              class="s3-img-item"
              v-for="image in s3ImageList"
              :key="image"
            >
              <img
                :src="image"
                @click="selectImage(image)"
              />
            </div>    
          </div>
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>

<script scoped>
import html2pdf from "html2pdf.js";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      target: "",
      myFont: "",
      diaryTitleList: [],
      diaryList: [],
      selectDiary: "",
      dialog: false,
      imageList: [],
      s3ImageList: [],
      myFontList: [],
      selectedItem: 1,
    };
  },
  computed: {
    ...mapGetters(["getMyFont"]),
  },
  methods: {
    dialogOn(th) {
      console.log(th);
      this.dialog = true;
      this.target = th;
    },
    exportToPDF() {
      // window.scrollTo(50, 0);
      html2pdf(this.$refs.pdfarea, {
        // margin: [0, 0, 0, 20],
        filename: "document.pdf",
        image: { type: "jpg", quality: 1.0 },
        html2canvas: {
          scale: 1,
          dpi: 300,
          letterRendering: true,
          allowTaint: true,
          useCORS: true,
          scrollY: 0,
        },
        jsPDF: {
          orientation: "l",
          unit: "mm",
          format: "letter",
          compressPDF: true,
        },
      });
    },
    getS3ImageList() {
      var selectDiaryId = -1;
      for (let i = 0; i < this.diaryList.length; i++) {
        if (this.selectDiary == this.diaryList[i].diaryTitle) {
          selectDiaryId = this.diaryList[i].id;
          break;
        }
      }
      this.$store.dispatch("getS3ImageList", selectDiaryId).then((res) => {
        this.s3ImageList = res.data;
      });
      this.dialog = true;
    },
    selectImage(image) {
      this.imageList[this.target] = image;
      this.dialog = false;
      console.log(image);
      this.target = "";
    },
    selectFont: function (font) {
      console.log("폰트 선택함", font);
      this.$store.commit("setMyFont", font);
    },
    appendImage() {
      this.imageList.push(
        "https://imgur.com/NRj7Qzh.png"
      );
      this.excuteDragElement();
    },
    appendArea() {
      const area = document.createElement("div");
      area.className = "albumContent";
      document.getElementById("album-contents-wrap").appendChild(area);
    },
    excuteDragElement() {
      var classes = document.getElementsByClassName("picContents");
      for (let i = 0; i < classes.length; i++) {
        this.dragElement(classes[i]);
      }
    },

    dragElement(elmnt) {
      var pos1 = 0,
        pos2 = 0,
        pos3 = 0,
        pos4 = 0;
      if (elmnt.children[0]) {
        /* if present, the header is where you move the DIV from:*/
        elmnt.children[0].onmousedown = dragMouseDown;
      } else {
        /* otherwise, move the DIV from anywhere inside the DIV:*/
        elmnt.onmousedown = dragMouseDown;
      }

      function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
      }

      function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        elmnt.style.top = elmnt.offsetTop - pos2 + "px";
        elmnt.style.left = elmnt.offsetLeft - pos1 + "px";
      }

      function closeDragElement() {
        /* stop moving when mouse button is released:*/
        document.onmouseup = null;
        document.onmousemove = null;
      }
    },
  },
  created() {
    (this.s3ImageList = []),
      (this.dialog = false),
      // 다이어리 목록 가져오기
      this.$store.dispatch("diaryGet").then((res) => {
        const tmp = [];
        for (let i = 0; i < res.data.length; i++) {
          tmp[i] = res.data[i].diaryTitle;
        }
        this.diaryTitleList = tmp;
        this.diaryList = res.data;
      });

    this.$store.dispatch("getUserFonts").then((res) => {
      console.log(res.data, "유저 폰트");
      this.myFontList = res.data;
    });

    //data를 가져오는 api 호출
    //가져온 data 중 image url 데이터를 blob파일로 변경하여 vue data에 넣어줌
    let imageUrl =
      "https://imgur.com/NRj7Qzh.png";
    fetch(imageUrl)
      .then((response) => response.blob())
      .then((blob) => {
        this.imageList.push(URL.createObjectURL(blob));
      });
  },
  mounted() {
    this.$store.commit("setMyFont", {
      fontName: "기본체",
      fontPrice: 0,
      fontUrl: "Pretendard Variable",
      id: 1,
    });
  },
  updated() {
    this.excuteDragElement();
  },
};
</script>

<style lang="scss">
#albumMenu {
  // background: #bbb;
  position: fixed;
  right: 59px;
  top: 25%;
}
#album-contents-wrap {
  width: 1055px;
  overflow: visible;
  position: relative;
  /* left: -4%; */
}
.albumContent {
  width: 1055px;
  max-height: 215.62mm;
  min-height: 215.62mm;
  padding: 20px;
  /* border : 1px solid black; */
  background-image: url("../../assets/image/albumBackground.jpg");
  background-size: cover;
  align-content: center;
}
.picContents {
  position: absolute;
  background-color: #fff;
  // padding: 20px;
  resize: both;
  overflow: auto;
  // box-shadow: 2px 2px 11px 2px rgba(35, 35, 35, 0.28);
}
.picContentsheader {
  padding: 10px;
  cursor: move;
  /* z-index: 10; */
  background-color: #ffb319;
  color: #fff;
}
.pic-content-wrap {
  background-color: #fff;
  padding: 20px;
  // width: 100%;
}
#fontLlist {
  height: 300px;
  overflow: auto;
}
#album-dialog-header {
  height: 42px;
  border-bottom: 1px solid #e7e7e7;
}
#album-dialog-close {
  float: right;
  line-height: 42px;
  cursor: pointer;
  margin-right: 7px;
}
.dialog-wrap {
  padding: 20px 40px;
  // background-color: #aaa;  
}
.get-img-btn {
  display: flex;
  width: 100%;
  margin: 0 auto 25px auto;
  box-shadow: none;
  color: #fff;
}
.picContents::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.img-desc {  
  display: block;
  margin: 0 auto;
  width: 100%;
  /* margin-bottom: 20px;  */
  background-color: white;
}
.s3-img-wrap {
  width: 680px;
  height: 100%;
  // background-color: yellowgreen;
  overflow: hidden;
}
.s3-img-item {
  display: inline-block;
  position: relative;
  width: 160px;
  height: 150px;
  cursor: pointer;
  line-height: 150px;
  margin: 0 9px 9px 0;
  background-color: #fff;
  overflow: hidden;
  // transition: 0.3s;

  img {
    width: 160px;
    height: 150px;
    object-fit: cover;
  }
}
// .s3-img-item::after {
//   content: '';
//   display: block;
//   clear: both;
// }
.s3-img-item:hover {
  opacity: .7;
  transition: 0.3s;
}
</style>