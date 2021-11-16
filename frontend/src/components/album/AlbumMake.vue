<template>
  <div style="text-align: center; padding: 10px;">

    <!-- 사이드 메뉴 -->
    <div  id="albumMenu" data-html2canvas-ignore="true">
      <v-btn @click="appendImage" style="margin-right: 10px;">사진 추가하기</v-btn>
      <v-btn @click="appendArea">페이지 추가하기</v-btn>
      <v-btn @click="exportToPDF" style="display: block; width: 100%; margin-top: 10px;">pdf 만들기</v-btn>

      <v-list rounded>
        <v-subheader>MY FONT</v-subheader>
        <v-list-item-group id="fontList" color="primary">
          <v-list-item v-for="(myFont, i) in myFontList" :key="i">
            <v-list-item-content @click="selectFont(myFont)" :style="{ 'font-family': myFont.fontUrl }">
              <v-list-item-title :style="{ 'font-family': myFont.fontUrl }">{{ myFont.fontName }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </div>

    <!-- 콘텐츠 --> 
    <div id="contents" ref="pdfarea" >
      <div class="albumContent"  style="height: 100%; overflow: auto;">       
        <div class="picContents" v-for="(image, idx) in imageList" :key="idx">
          <div class="picContentsheader" data-html2canvas-ignore="true">Click here to move</div>
          <img :src="image" @click="dialogOn(idx)" style="width: 80%; margin-top: 20px;"/>
          <textarea name="content" id="ucontent" rows="19" cols="70" placeholder="내용을 입력하세요" 
            :style="{ 'font-family': getMyFont.fontUrl }"/>
        </div>
      </div>
    </div>
    
    <!-- 다이얼로그 --> 
    <v-dialog id="getImageForm" v-model="dialog" style="width: 800px;">

      <!-- 사진 가져오기 --> 
      <div>
        <v-combobox
          v-model="selectDiary"
          :items="diaryTitleList"
          label="일기장 선택"
          style="width: 80%; margin: auto;"
        ></v-combobox>
        
        <v-btn @click="getS3ImageList" style="width: 80%; margin-bottom: 5px;">이미지 가져오기</v-btn>        
      </div>
      <v-card class="s3contents" v-for="image in s3ImageList" style="display: inline-block;"
          :key="image">
          <img 
            :src="image" 
            @click="selectImage(image)"
            style="width: 50%; vertical-align: middle;"/>
      </v-card>
      <br>
      <v-btn @click="dialog=false" style="width: 80%; margin-top: 5px;">닫기</v-btn>
    </v-dialog>
    
  </div>
</template>

<script>
import html2pdf from 'html2pdf.js';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      target: '',
      myFont: '',
      diaryTitleList:[],
      diaryList:[],
      selectDiary:'',
      dialog: false,  
      imageList: [],
      s3ImageList: [],
      myFontList: [],
      selectedItem: 1,
    }
  }, 
  computed: {
    ...mapGetters(['getMyFont'])
  },
  methods: {
      dialogOn(th) {
        console.log(th)
        this.dialog = true;
        this.target = th;
      },
      exportToPDF () {
          // window.scrollTo(50, 0);
          html2pdf(this.$refs.pdfarea, {
              // margin: [0, 0, 0, 20],
              filename: 'document.pdf',
              image: {type:"jpg", quality: 1.0},
              html2canvas: { scale:1, dpi: 300, letterRendering: true, allowTaint: true , useCORS: true, scrollY: 0 },
              jsPDF: { orientation: 'l', unit: 'mm', format: 'letter', compressPDF: true }
          })
      },
      getS3ImageList() {
        var selectDiaryId = -1 
        for(let i=0;i<this.diaryList.length;i++){
          if(this.selectDiary == this.diaryList[i].diaryTitle){
            selectDiaryId = this.diaryList[i].id
            break
          }
        }
        this.$store.dispatch('getS3ImageList', selectDiaryId).then((res) => {
          this.s3ImageList = res.data
        })
        this.dialog = true
      },
      selectImage(image) {
        this.imageList[this.target] = image
        this.dialog = false;
        console.log(image)
        this.target = ''
      },
      selectFont: function (font) {
        console.log('폰트 선택함', font)
        this.$store.commit("setMyFont", font)
      },
      appendImage() {
        this.imageList.push('data:image/jpeg;base64,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')
        this.excuteDragElement()
      },
      appendArea() {
        const area = document.createElement('div')
        area.className = "albumContent"
        document.getElementById('contents').appendChild(area)
      },
      excuteDragElement() {
        var classes = document.getElementsByClassName('picContents')
        for(let i = 0; i < classes.length; i++){
          this.dragElement(classes[i])
        }
      },

      dragElement(elmnt) {
        var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
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
          elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
          elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
        }

        function closeDragElement() {
          /* stop moving when mouse button is released:*/
          document.onmouseup = null;
          document.onmousemove = null;
        }
      },
  },
  created() {
    this.s3ImageList = [],
    this.dialog = false,
    // 다이어리 목록 가져오기
    this.$store.dispatch("diaryGet").then((res) => {
      const tmp = []
      for(let i=0;i<res.data.length;i++) {
        tmp[i] = res.data[i].diaryTitle
      }
      this.diaryTitleList = tmp
      this.diaryList = res.data
    });

    this.$store.dispatch("getUserFonts")
      .then((res) => {
        console.log(res.data, '유저 폰트')
        this.myFontList = res.data
      })

    //data를 가져오는 api 호출
    //가져온 data 중 image url 데이터를 blob파일로 변경하여 vue data에 넣어줌
    let imageUrl = 'data:image/jpeg;base64,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';
    fetch( imageUrl )
      .then(response => response.blob())
      .then(blob => {
          this.imageList.push(URL.createObjectURL(blob));
      })
  },
  updated() {
    this.excuteDragElement()
  }
}
</script>

<style>
#albumMenu {
  position: fixed;
  right: 59px;
  top: 370px;
}
.albumContent {
  width: 1055px;
  max-height: 215.62mm;
  min-height: 215.62mm;
  padding: 20px;
  /* border : 1px solid black; */
  background-image: url('../../assets/image/albumBackground.jpg');
  background-size: cover;
  align-content: center;
}
#contents {
  width: 1055px;
  overflow: visible;
  position: relative;
  /* left: -4%; */
}

.picContents {
  position: absolute;
  /* z-index: 9; */
  background-color: white;
  text-align: center;
  /* border: 1px solid black; */
  resize: both;
  overflow: auto;
  /* box-shadow: grey 6px 8px 10px 5px; */
}

.picContentsheader {
  padding: 10px;
  cursor: move;
  /* z-index: 10; */
  background-color: #ffb319;
  color: #fff;
}

#fontLlist {
  height: 300px;
  overflow: auto;
}

.v-btn {
  background-color: orange;
}

.v-dialog {
  width: 850px;
  text-align: center;
  padding: 20px;
  background-color: white;
  border-radius: 20px;
}

.v-dialog::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}

.picContents::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}

textarea {
  width: 80%; 
  height: 33px; 
  display: block; 
  margin: 0 auto; 
  /* margin-bottom: 20px;  */
  border-radius: 20px; 
  background-color: white; 
  text-align: center;
}

.s3contents {
  width: 23%; 
  height: 150px; 
  display: inline-block; 
  line-height: 150px; 
  margin: 5px;
  /* background-color: red !important; */
  transition: 0.3s
}

.s3contents:hover {
  background-color: #f3fc77 !important;
}

</style>