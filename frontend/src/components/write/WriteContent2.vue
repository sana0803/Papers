<template>
  <div id="WriteContent_Container">
    <div id="WriteContent_Templete">
      <div class="select-diary-section">
        <!-- <v-combobox
          v-model="selectDiary"
          :items="diaryTitleList"
          label="일기장 선택"
        ></v-combobox> -->
        <v-select
          v-model="selectDiary"
          :items="diaryTitleList"
          label="일기장 선택"          
          hide-details
          color="#FFB319"
        ></v-select>
      </div>
      <div class="title-section">
        <v-text-field
          v-model="note.noteTitle"
          :style="{ 'font-family': getMyFont.fontUrl }"
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
        <v-textarea
          v-model="note.noteContent"
          :style="{ 'font-family': getMyFont.fontUrl }"
          name="content"
          id="ucontent"
          label="내용을 입력하세요"
          color="#979797"
          rows="10"
          counter="400"
          maxlength="400"
          single-line
        ></v-textarea>
        <!-- <textarea name="content" id="ucontent" counter maxlength="120" rows="10" cols="70" placeholder="내용을 입력하세요" v-model="note.noteContent" :style="{ 'font-family': getMyFont.fontUrl }"> -->
        <!-- </textarea> -->
      </div>
      <div class="file-section">
        <v-file-input
          v-model="note.noteMediaList"
          id="file"
          label="사진을 등록하세요 (최대 4장, 각 용량 2MB 이하)"
          color="#979797"
          counter
          multiple
          show-size
          small-chips
          truncate-length="15"
          prepend-icon="mdi-camera"
        >
        </v-file-input>
        <input type="file" id="file" ref="files" @change="onImgUpload" multiple />
        <div>
          <div
            v-for="(file, idx) in files"
            :key="idx"
            class="img-preview"
          >
            <img :src="file.preview" />
          </div>
        </div>
        <div
          class="img-section"
          v-for="(media, idx) in note.noteS3MediaList"
          :key="idx"
        >
          <img :src="media"/>
        </div>
      </div>
    </div>
    <div id="HashTag_Input">
      <v-text-field
        v-model="note.noteHashtagList"
        label="#여기에 #해시태그를 #입력하세요"
        color="#FFB319"
      ></v-text-field>
    </div>
    <div id="WriteContent_Btn">
      <v-btn @click="write" id="Write_Btn">작성</v-btn>
      <v-btn @click="back" id="Back_Btn"> 취소 </v-btn>
    </div>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      diaryTitleList:[],
      diaryList:[],
      selectDiary:'',
      myFont: '',
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
      note: {
        noteId: '',
        diaryId: '',
        fontId: '',
        layoutId: 1,
        designId: 1,
        writerId: '',
        noteTitle: '',
        noteContent: '',
        noteS3MediaList: [],
        noteMediaList: [],
        noteHashtagList: '',
        stickerList: [{
          leftPixel: '',
          stickerId: '',
          topPixel: '',
        }],
        emotionList: [{
          writerId: '',
          emotionInfoId: '',
          noteId: '',
        }]
      },
    }
  },
  computed: {
    loginUser() {
      return this.$store.getters.getLoginUser;
    },
    // fontSetting () {
    //   return this.$store.getters['getMyFont'];
    // }
    ...mapGetters(['getMyFont'])
  },
  methods: {
    onImgUpload () {
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
            number: i
          }
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
    write() {
      var selectDiaryId = -1 
      for(let i=0;i<this.diaryList.length;i++){
        if(this.selectDiary == this.diaryList[i].diaryTitle){
          selectDiaryId = this.diaryList[i].id
          break
        }
      }
      console.log(this.getMyFont.id, '폰트아이디값')
      const tmp = this.note.noteHashtagList.split("#")
      const noteHashtagList = []
      for(let i=1;i<tmp.length;i++){
        noteHashtagList[i-1] = tmp[i]
      }

      const formData = new FormData()
      formData.append('designId', this.note.designId)
      formData.append('diaryId', selectDiaryId)
      // for(let i = 0; i < this.note.emotionList.length; i++){
      //   formData.append('emotionList.writerId[]', this.note.emotionList[i].writerId)
      //   formData.append('emotionList.emotionInfoId[]', this.note.emotionList[i].emotionInfoId)
      //   formData.append('emotionList.noteId[]', this.note.emotionList[i].noteId)
      // }
      formData.append('fontId', this.getMyFont.id)
      formData.append('layoutId', this.note.layoutId)
      formData.append('noteContent', this.note.noteContent)
      formData.append('noteHashtagList', noteHashtagList)
      for(let i = 0; i < this.note.noteS3MediaList.length; i++){
        formData.append('noteS3MediaList[]', this.note.noteS3MediaList[i])
      }      
      for(let i = 0; i < this.note.noteMediaList.length; i++){
        formData.append('noteMediaList[]', this.note.noteMediaList[i])  
      }
      formData.append('noteTitle', this.note.noteTitle)      
      for(let i = 0; i < this.note.stickerList.length; i++){
        formData.append('stickerList.leftPixel[]', this.note.stickerList[i].leftPixel)
        formData.append('stickerList.stickerId[]', this.note.stickerList[i].stickerId)
        formData.append('stickerList.topPixel[]', this.note.stickerList[i].topPixel)
      }      
      formData.append('writerId', this.loginUser.userNickname)

      if(this.$store.getters['getIsUpdate'] == false){
        this.$store.dispatch("write", formData).then(() => {
          Swal.fire({
              icon: "success",
              title:
                '<span style="font-size:25px;">일기 작성 완료.</span>',
              confirmButtonColor: "#b0da9b",
              confirmButtonText: '<span style="font-size:18px;">확인</span>',
            });
          // console.log(res.data);
          this.$store.commit('initNoteContent')
          const loginUser = this.$store.getters['getLoginUser']
          loginUser.userMileage += 10;
          this.$store.commit('setLoginUser', loginUser);
          this.$router.push('/main')
        });
      }
      else if(this.$store.getters['getIsUpdate'] == true) {
        const note = {
          noteId: this.note.noteId,
          formData : formData
        }
        this.$store.dispatch("modifyNote", note).then(() => {
          Swal.fire({
              icon: "success",
              title:
                '<span style="font-size:25px;">일기 수정 완료.</span>',
              confirmButtonColor: "#b0da9b",
              confirmButtonText: '<span style="font-size:18px;">확인</span>',
            });
          // console.log(res.data);
          this.$store.commit('initNoteContent')
          this.$router.push('/main')
        });
      }
    },
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
    }
};
</script>

<style lang="scss" scoped>
#WriteContent_Container {
  width: 530px;
  height: 854px;
  // background-color: #bbb;
}
#WriteContent_Templete {  
  background-color: #FFF;
  overflow: hidden;
  height: 684px;
  padding: 22px;
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
  height: 325px;
  overflow: hidden;
}
.file-section {
  background-color: #fff;
  // height: 100%;
}
.img-preview {
  background: lightsteelblue;
  display: inline-block;
  margin-top: 10px;
  margin-right: 10px;
  border-radius: 4px;
  overflow: hidden;
  z-index: 10;
  width: 80px;
  height: 80px; 
  
  img {
    width: 100%;
    height: auto;
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
  margin-top: 30px;
  // background-color: #eee;
}
#WriteContent_Btn {
  height: 38px;
  width: 152px;
  margin: 0 auto;
  margin-top: 30px;
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
</style>