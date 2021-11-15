<template>
  <div id="write-wrap">
    <div id="WriteContent_Container">
      <div id="WriteContent_Templete">
        <div class="select-diary-section">
          <v-select
            v-model="selectDiary"
            :items="diaryTitleList"
            label="일기장 선택"          
            hide-details
            height="25px"
            color="#FFB319"
          ></v-select>
        </div>
        <div class="title-section">
          <v-text-field
            :style="{ 'font-family': getMyFont.fontUrl }"          
            v-model="note.noteTitle"          
            name="title"
            id="utitle"
            label="제목을 입력하세요"
            color="#979797"
            counter="20"
            maxlength="20"            
            single-line
            required
          ></v-text-field>        
        </div>          
        <div class="content-section">
            <!-- class="target-font" -->
          <v-textarea
            :style="{ 'font-family': getMyFont.fontUrl }"
            v-model="note.noteContent"
            name="content"
            id="ucontent"
            label="내용을 입력하세요"
            color="#979797"
            rows="3"
            counter="400"
            maxlength="400"
            single-line
          ></v-textarea>
        </div>        
        <label for="file">
          <span style="font-weight: 600; cursor: pointer;">내 컴퓨터에서 업로드  ></span>
        </label>
        <div class="file-input">
          <input
            type="file"
            id="file"
            ref="files"
            multiple
            @change="onImgUpload"
            style="opacity: 0; cursor: pointer; visibility: none; background: red"
          />
        </div>
          <!-- <v-file-input
            v-model="note.noteMediaList"
            class="target-file"
            label="사진을 등록하세요 (최대 4장, 각 용량 2MB 이하)"
            color="#979797"
            counter
            multiple
            show-size
            small-chips
            truncate-length="11"
            prepend-icon="mdi-camera"
            @change="onImgUpload"
          >
          </v-file-input> -->
          <!-- <label for="file"> -->
            <!-- <span style="font-weight: 500;">내 드라이브에서 업로드</span> -->
          <!-- </label> -->
          <!-- <v-file-input
            v-model="note.noteMediaList"
            class="target-file"
            label="사진을 등록하세요 (최대 4장, 각 용량 2MB 이하)"
            color="#979797"
            counter
            multiple
            show-size
            small-chips
            truncate-length="11"
            prepend-icon="mdi-camera"
          >
          </v-file-input> -->
        <div class="file-section">
          <div v-if="note.noteS3MediaList.length > 0" sytle="height:300px;">
            <v-carousel
              hide-delimiters
              height="300"
              style="height:300px;">
              <v-carousel-item
                v-for="(media, idx) in note.noteS3MediaList"
                :key="idx"
                :src="media"
                class="img-preview"
              ></v-carousel-item>
              <v-carousel-item
                v-for="(file, idx) in files"
                :key="idx"
                :src="file.preview"
                class="img-preview"
              ></v-carousel-item>
            </v-carousel>
            <!-- <div
              class="img-section"
              v-for="(media, idx) in note.noteS3MediaList"
              :key="idx"
            >
              <img :src="media"/>
            </div> -->
            <!-- <div
              v-for="(file, idx) in files"
              :key="idx"
              class="img-preview"
            >
              <img :src="file.preview" />
            </div> -->
          </div>
          <div v-else style="height:300px;">
            <v-carousel
              hide-delimiters
              height="300"
              style="height:300px;">
              <v-carousel-item
                v-for="(file, idx) in files"
                :key="idx"
                :src="file.preview"
                class="img-preview"
              ></v-carousel-item>
                <!-- class="diary-content-img" -->
            </v-carousel>
            <!-- <div
              v-for="(file, idx) in files"
              :key="idx"
              class="img-preview"
            >
              <img :src="file.preview" />
            </div> -->
          </div>
          </div>
        </div>
        <div id="HashTag_Input">
          <v-text-field
            hide-details
            v-model="note.noteHashtagList"
            label="#여기에 #해시태그를 #입력하세요"
            color="#FFB319"
          ></v-text-field>
        </div>
      </div>
    <div id="WriteContent_Btn">
      <v-btn @click="writeFin" id="Write_Btn">작성</v-btn>
      <v-btn @click="back" id="Back_Btn">취소</v-btn>
    </div>
    <!-- <div id="prev-wrap" v-show="onPreview">
      <PreviewContent />
    </div>
    <div v-if="!onPreview" id="WriteContent_Btn">
      <v-btn @click="write" id="Write_Btn">다음</v-btn>
      <v-btn @click="back" id="Back_Btn"> 취소 </v-btn>
    </div> -->
  </div>
</template>

<script>
import Swal from "sweetalert2";
// import { mapGetters } from 'vuex';
import EventBus from '../../eventBus'
// import PreviewContent from "../../components/write/PreviewContent.vue";

export default {
  components: {
    // PreviewContent,
  },
  data() {
    return {
      diaryTitleList: [],
      diaryList: [],
      selectDiary: "",
      myFont: "",
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
      onPreview: false,
      note: {
        noteId: '',
        diaryId: '',
        fontId: 1,
        layoutId: 1,
        designId: 1,
        writerId: "",
        noteTitle: "",
        noteContent: "",
        noteS3MediaList: [],
        noteMediaList: [],
        noteHashtagList: '',
        stickerList: [],
        emotionList: [{
          writerId: '',
          emotionInfoId: '',
          noteId: '',
        }]
      },
      stickerNum: 1
    }
  },
  computed: {
    loginUser() {
      return this.$store.getters.getLoginUser;
    },
    // fontSetting () {
    //   return this.$store.getters['getMyFont'];
    // }
    // ...mapGetters(["getMyFont"]),
    getMyFont() {
      // v-text-field에 폰트 적용하기
      const target = document.getElementsByClassName("v-text-field__slot")
      // 해시태그에 폰트적용 빼기위해서 -1함
      for (let i = 0; i < target.length-1; i++) {
        target[i].style.fontFamily = this.$store.getters.getMyFont.fontUrl;
        console.log(target[i]);
      }
      return this.$store.getters["getMyFont"];
    },
  },
  methods: {    
    // onImgUpload() {
    //   // console.log(this.$refs.files.files);
    //   const target = document.getElementsByClassName("target-file")
    //   console.log(target.files)
    //   // this.files = [...this.files, this.$refs.files.files];
    //   //하나의 배열로 넣기
    //   let num = -1;
    //   for (let i = 0; i < target.length; i++) {
    //     this.files = [
    //       ...this.files,
    //       //이미지 업로드
    //       {
    //         //실제 파일
    //         file: target.files[i],
    //         //이미지 프리뷰
    //         preview: URL.createObjectURL(target.files[i]),
    //         //삭제및 관리를 위한 number
    //         number: i,
    //       },
    //     ];
    //     num = i;
    //     //이미지 업로드용 프리뷰
    //     // this.filesPreview = [
    //     //   ...this.filesPreview,
    //     //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
    //     // ];
    //   }
    //   this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
    //   console.log(this.files);
    // },
    onImgUpload(e) {
      console.log(e.target.files)
      console.log(this.$refs.files.files);
      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i,
          },
        ];
        num = i;
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
    },
    back() {
      this.$router.go(-1);
    },
    // write() {
    //   var selectDiaryId = -1;
    //   for (let i = 0; i < this.diaryList.length; i++) {
    //     if (this.selectDiary == this.diaryList[i].diaryTitle) {
    //       selectDiaryId = this.diaryList[i].id;
    //       break;
    //     }
    //   }
    //   for(let i=1;i<this.stickerNum;i++){
    //     var temp = document.getElementById('sticker' + i) 
    //     var item = {
    //       leftPixel: temp.style.left,
    //       stickerId: temp.className,
    //       topPixel: temp.style.top
    //     }
    //     this.note.stickerList.push(item)
    //   }

    //   const tmp = this.note.noteHashtagList.split("#")
    //   const noteHashtagList = []
    //   for(let i=1;i<tmp.length;i++){
    //     noteHashtagList[i-1] = tmp[i]
    //   }

    //   const formData = new FormData();
    //   formData.append("designId", this.note.designId);
    //   formData.append("diaryId", selectDiaryId);
    //   // for(let i = 0; i < this.note.emotionList.length; i++){
    //   //   formData.append('emotionList.writerId[]', this.note.emotionList[i].writerId)
    //   //   formData.append('emotionList.emotionInfoId[]', this.note.emotionList[i].emotionInfoId)
    //   //   formData.append('emotionList.noteId[]', this.note.emotionList[i].noteId)
    //   // }
    //   formData.append("fontId", this.getMyFont.id);
    //   formData.append("layoutId", this.note.layoutId);
    //   formData.append("noteContent", this.note.noteContent);
    //   formData.append("noteHashtagList", noteHashtagList);
    //   for (let i = 0; i < this.note.noteS3MediaList.length; i++) {
    //     formData.append("noteS3MediaList[]", this.note.noteS3MediaList[i]);
    //   }
    //   for (let i = 0; i < this.$refs.files.files.length; i++) {
    //     formData.append("noteMediaList[]", this.$refs.files.files[i]);
    //   }
    //   formData.append("noteTitle", this.note.noteTitle);
    //   for (let i = 0; i < this.note.stickerList.length; i++) {
    //     formData.append(
    //       "stickerList.leftPixel[]",
    //       this.note.stickerList[i].leftPixel
    //     );
    //     formData.append(
    //       "stickerList.stickerId[]",
    //       this.note.stickerList[i].stickerId
    //     );
    //     formData.append(
    //       "stickerList.topPixel[]",
    //       this.note.stickerList[i].topPixel
    //     );
    //   }
    //   formData.append('noteTitle', this.note.noteTitle)      
    //   for(let i = 0; i < this.note.stickerList.length; i++){
    //     formData.append('stickerList[' + i + '].leftPixel', this.note.stickerList[i].leftPixel)
    //     formData.append('stickerList[' + i + '].stickerId', this.note.stickerList[i].stickerId)
    //     formData.append('stickerList[' + i + '].topPixel', this.note.stickerList[i].topPixel)
    //   }      
    //   formData.append('writerId', this.loginUser.userNickname)

    //   console.log(formData)
    //   console.log('프리뷰 띄우기') 
    //   this.onPreview = true;    

    //   if(this.$store.getters['getIsUpdate'] == false){
    //     this.$store.dispatch("write", formData).then(() => {
    //       Swal.fire({
    //         icon: "success",
    //         title: '<span style="font-size:25px;">일기 작성 완료.</span>',
    //         confirmButtonColor: "#b0da9b",
    //         confirmButtonText: '<span style="font-size:18px;">확인</span>',
    //       });
    //       // console.log(res.data);
    //       this.$store.commit("initNoteContent");
    //       const loginUser = this.$store.getters["getLoginUser"];
    //       loginUser.userMileage += 10;
    //       this.$store.commit("setLoginUser", loginUser);
    //     });
    //   } else if (this.$store.getters["getIsUpdate"] == true) {
    //     const note = {
    //       noteId: this.note.noteId,
    //       formData: formData,
    //     };
    //     this.$store.dispatch("modifyNote", note).then(() => {
    //       Swal.fire({
    //         icon: "success",
    //         title: '<span style="font-size:25px;">일기 수정 완료.</span>',
    //         confirmButtonColor: "#b0da9b",
    //         confirmButtonText: '<span style="font-size:18px;">확인</span>',
    //       });
    //       // console.log(res.data);
    //       this.$store.commit("initNoteContent");
    //     });
    //   }
    //   // this.$store.dispatch("getDiaryContent", this.note.diaryId).then((res) => {
    //   //   // this.$store.commit('setCurrentDiary', res.data)
    //   //   this.$store.commit('setNoteContent', this.note) // mutaion 호출 ('뮤테이션 이름, 매개변수)        
    //   //   console.log('프리뷰 노트 가져오기')
    //   //   console.log(res.data)
    //   // });    
    // },
    writeFin() {
      var selectDiaryId = -1;
      for (let i = 0; i < this.diaryList.length; i++) {
        if (this.selectDiary == this.diaryList[i].diaryTitle) {
          selectDiaryId = this.diaryList[i].id;
          break;
        }
      }
      for(let i=1;i<this.stickerNum;i++){
        var temp = document.getElementById('sticker' + i) 
        var item = {
          leftPixel: temp.style.left,
          stickerId: temp.className,
          topPixel: temp.style.top
        }
        this.note.stickerList.push(item)
      }

      const tmp = this.note.noteHashtagList.split("#")
      const noteHashtagList = []
      for(let i=1;i<tmp.length;i++){
        noteHashtagList[i-1] = tmp[i]
      }

      const formData = new FormData();
      formData.append("designId", this.note.designId);
      formData.append("diaryId", selectDiaryId);
      // for(let i = 0; i < this.note.emotionList.length; i++){
      //   formData.append('emotionList.writerId[]', this.note.emotionList[i].writerId)
      //   formData.append('emotionList.emotionInfoId[]', this.note.emotionList[i].emotionInfoId)
      //   formData.append('emotionList.noteId[]', this.note.emotionList[i].noteId)
      // }
      formData.append("fontId", this.getMyFont.id);
      formData.append("layoutId", this.note.layoutId);
      formData.append("noteContent", this.note.noteContent);
      formData.append("noteHashtagList", noteHashtagList);
      for (let i = 0; i < this.note.noteS3MediaList.length; i++) {
        formData.append("noteS3MediaList[]", this.note.noteS3MediaList[i]);
      }
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        formData.append("noteMediaList[]", this.$refs.files.files[i]);
      }
      formData.append("noteTitle", this.note.noteTitle);
      for (let i = 0; i < this.note.stickerList.length; i++) {
        formData.append(
          "stickerList.leftPixel[]",
          this.note.stickerList[i].leftPixel
        );
        formData.append(
          "stickerList.stickerId[]",
          this.note.stickerList[i].stickerId
        );
        formData.append(
          "stickerList.topPixel[]",
          this.note.stickerList[i].topPixel
        );
      }
      formData.append('noteTitle', this.note.noteTitle)      
      for(let i = 0; i < this.note.stickerList.length; i++){
        formData.append('stickerList[' + i + '].leftPixel', this.note.stickerList[i].leftPixel)
        formData.append('stickerList[' + i + '].stickerId', this.note.stickerList[i].stickerId)
        formData.append('stickerList[' + i + '].topPixel', this.note.stickerList[i].topPixel)
      }      
      formData.append('writerId', this.loginUser.userNickname)

      console.log(formData)

      if(this.$store.getters['getIsUpdate'] == false){
        this.$store.dispatch("write", formData).then(() => {
          Swal.fire({
            icon: "success",
            title: '<span style="font-size:25px;">일기 작성 완료.</span>',
            confirmButtonColor: "#b0da9b",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
          });
          // console.log(res.data);
          this.$store.commit("initNoteContent");
          const loginUser = this.$store.getters["getLoginUser"];
          loginUser.userMileage += 10;
          this.$store.commit("setLoginUser", loginUser);
          this.$router.push("/main");
        });
      } else if (this.$store.getters["getIsUpdate"] == true) {
        const note = {
          noteId: this.note.noteId,
          formData: formData,
        };
        this.$store.dispatch("modifyNote", note).then(() => {
          Swal.fire({
            icon: "success",
            title: '<span style="font-size:25px;">일기 수정 완료.</span>',
            confirmButtonColor: "#b0da9b",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
          });
          // console.log(res.data);
          this.$store.commit("initNoteContent");
          this.$router.push("/main");
        });
      }
    },
    dragElement(elmnt) {
          var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
          if (document.getElementById(elmnt.id)) {
            /* if present, the header is where you move the DIV from:*/
            document.getElementById(elmnt.id).onmousedown = dragMouseDown;
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
        }
  },
  created() {
      // 만약 수정하는 상태이면, state에 저장된 노트 컨텐츠들 가져오기
      if(this.$store.getters['getIsUpdate'] == true) {
        this.note = this.$store.getters['getNoteContent']
        console.log(this.note)
      }
      this.$store.dispatch("diaryGet").then((res) => {
        const tmp = []
        for(let i=0;i<res.data.length;i++) {
          tmp[i] = res.data[i].diaryTitle
        }
        this.diaryTitleList = tmp
        this.diaryList = res.data
      });

      EventBus.$on('createSticker', (sticker) => {
        const box = document.getElementById('WriteContent_Templete')

        // const div = document.createElement('div')
        const img = document.createElement('img')

        img.id = 'sticker' + this.stickerNum
        this.stickerNum++

        // div.append(img)

        img.src = sticker.stickerUrl

        img.style.width = '50px'
        img.style.height = '50px'
        img.style.position = 'absolute'
        img.style.top = '10px'
        img.style.left = '10px'
        img.style.cursor = 'pointer'
        
        img.className = sticker.id

        box.append(img)

        this.dragElement(img);
      })
    this.$store.dispatch("diaryGet").then((res) => {
      const tmp = [];
      for (let i = 0; i < res.data.length; i++) {
        tmp[i] = res.data[i].diaryTitle;
      }
      this.diaryTitleList = tmp;
      this.diaryList = res.data;
    });
  }
}
</script>

<style lang="scss" scoped>
#write-wrap {
  width: 100%;
  height: 100vh;
  padding-top: 8%;
  position: relative;
  // background-color: lightblue;
}
#prev-wrap {
  position: absolute;
  top: 0;
  left: auto;
  // background-color: lightpink;
  width: 100%;
}
#WriteContent_Container {
  margin: 0 auto;
  // width: 530px;
  width: 443px;
  // height: 804px;
  height: 610px;
  background-color: #fff;
}
#WriteContent_Templete {
  position:relative;
  // height: 684px;
  height: 610px;
  padding: 14px 36px 36px 30px;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
}
.select-diary-section {
  // background-color: burlywood;
  width: 40%;
  margin: none;
  padding: none;
  div {
    // background-color: red;
    padding: none;
    margin: none;
    cursor: pointer;
  }
}
.v-input__control {
  background-color: red;
  padding: none;
}
.title-section {
  // background-color: lightblue;
  margin: none;
  padding: none;
}
.content-section {
  // background-color: lemonchiffon;
  height: 125px;
  overflow: hidden;
}
.file-section {
  background-color: #f7f7f7;
  width: 100%;
  height: 300px;
  margin-top: 5px;
  overflow: hidden;
  // height: 100%;
}
.file-input {
  // background-color: greenyellow;
  position: absolute;
  width: 5px;
  height: 5px;
  right: 0;
  top: 0;
}
.img-preview {
  // background: #eee;
  display: inline-block;
  // margin-top: 12px;
  // margin-right: 10px;
  // border-radius: 4px;
  // width: 80px;
  object-fit: contain;
  height:300px;
  width: 100%;
  
  img {
    width: 100%;
    // height: auto;
  }
}
.img-section {
  background: lightpink;
  display: inline-block;
  margin-top: 10px;
  margin-right: 10px;
  border-radius: 4px;
  overflow: hidden;
  width: 80px;
  height: 80px;

  img {
    width: 100%;
    // height: 100%;
    // background: green;
  }
}
#HashTag_Input {
  margin-top: 10px;
  // background-color: #eee;
}
#WriteContent_Btn {
  height: 38px;
  width: 152px;
  margin: 0 auto;
  margin-top: 84px;
  box-shadow: none;  
}
#Write_Btn {
  background: #ffb319;
  color: white;
  font-size: 17px;
  width: 70px;
  box-shadow: none;
}
#Back_Btn {
  background: #9f9f9f;
  color: white;
  font-size: 17px;
  width: 70px;
  margin-left: 12px;
  box-shadow: none;
}
textarea {
  letter-spacing: 1px;
}
textarea {
  padding: 10px;
  max-width: 100%;
  line-height: 1.5;
  border-radius: 5px;
  border-bottom: 1px solid #ccc;
}
.sticker{
  width:300px;
  height:300px;
}
</style>