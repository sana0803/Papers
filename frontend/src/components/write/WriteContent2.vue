<template>
  <div id="WriteContent_Container">
    <div id="WriteContent_Templete">
      <v-combobox
        v-model="selectDiary"
        :items="diaryTitleList"
        label="일기장 선택"
      ></v-combobox>
      <div id="in_title">
        <textarea name="title" id="utitle" rows="1.5" cols="70" placeholder="제목을 입력하세요" maxlength="100" v-model="note.noteTitle" :style="{ 'font-family': getMyFont.fontUrl }"></textarea>
      </div>
      <div id="in_content">
        <textarea name="content" id="ucontent" rows="19" cols="70" placeholder="내용을 입력하세요" v-model="note.noteContent" :style="{ 'font-family': getMyFont.fontUrl }">
        </textarea>
      </div>
      <v-file-input
        v-model="note.noteMediaList"
        multiple
        small-chips
        truncate-length="15"
      ></v-file-input>
    </div>
    <div>
      <div v-for="media in note.noteS3MediaList" :key="media">
        <img :src="media" style="width: 100px;"/>
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
      for(let i = 0; i < this.note.emotionList.length; i++){
        formData.append('emotionList.writerId[]', this.note.emotionList[i].writerId)
        formData.append('emotionList.emotionInfoId[]', this.note.emotionList[i].emotionInfoId)
        formData.append('emotionList.noteId[]', this.note.emotionList[i].noteId)
      }
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
}
#WriteContent_Templete {
  height: 684px;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
}
#HashTag_Input {
  margin-top: 30px;
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