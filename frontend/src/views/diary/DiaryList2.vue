<template>
  <div>
    <div id="Line"></div>
    <!-- Left -->
    <div id="List_Left">
      <div class="Img_Box">
        <div v-for="(note,idx) in noteList1" :key="note.noteId" class="Img_Item">
          <!-- <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div> -->
          <v-checkbox
            v-if="editMode && note.writerId == loginUser.userId"
            class="check"
            v-model="selected[idx]"
            :key="note.noteId"
            :value="note.noteId"
            color="orange"
          ></v-checkbox>
          <img v-if="!editMode" class="Img" :src="note.noteMediaList[0]" @click="goDetailNote(note)" />
          <img v-if="editMode" class="Img2" :src="note.noteMediaList[0]" />
        </div>
      </div>
    </div>
    <!-- Right -->
    <div id="List_Right">
      <div v-if="!editMode" class="List_Header">
        <span @click="change(editMode)" class="Header_txt">편집</span>
      </div>
      <div v-if="editMode" class="List_Header">
        <span @click="modifyMode" class="Header_txt" style="margin-right: 13px"
          >일기 이동</span
        >
        <span @click="deleteMode" class="Header_txt" style="margin-right: 13px"
          >삭제</span
        >
        <span @click="change(editMode)" class="Header_txt">취소</span>
      </div>
      <div class="Img_Box">
        <div v-for="(note,idx) in noteList2" :key="note.noteId" class="Img_Item">
          <!-- <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div> -->
          <v-checkbox
            v-if="editMode && note.writerId == loginUser.userId"
            class="check"
            v-model="selected[idx]"
            :key="note.noteId"
            :value="note.noteId"
            color="orange"
          ></v-checkbox>
          <img v-if="!editMode" class="Img" :src="note.noteMediaList[0]" @click="goDetailNote(note)" />
          <img v-if="editMode" class="Img2" :src="note.noteMediaList[0]" />
        </div>
      </div>
    </div>
    <!-- delete Dialog -->
    <v-dialog v-model="deleteDialog" persistent max-width="286">
      <v-card class="Dialog">
        <div class="Dialog_Header">
          <v-icon
            @click="dialog = false"
            class="Dialog_Close"
            style="font-size: 1.5em"
            >close</v-icon
          >
        </div>
        <div class="Dialog_Content">
          <div class="Dialog_Text">선택한 일기를<br />삭제하시겠습니까?</div>
          <div class="Dialog_Btn_Box">
            <v-btn @click="deleteItems" style="background: #ffb319; color: white" class="Dialog_Btn"
              >삭제</v-btn
            >
            <v-btn
              @click="deleteDialog = false"
              style="background: #9f9f9f; color: white"
              class="Dialog_Btn"
              >취소</v-btn
            >
          </div>
        </div>
      </v-card>
    </v-dialog>
    <!-- modify Dialog -->
    <v-dialog v-model="modifyDialog" persistent max-width="286">
      <v-card class="Dialog">
        <div class="Dialog_Header">
          <v-icon
            @click="dialog = false"
            class="Dialog_Close"
            style="font-size: 1.5em"
            >close</v-icon
          >
        </div>
        <div class="Dialog_Content">
          <div class="Dialog_Combo">
            일기 이동
            <v-select color="#ffb319" :items="diaryTitleList" v-model="selectDiary" outlined dense style="margin-top: 10px"></v-select>
          </div>
          <div class="Dialog_Btn_Box" style="margin-top: 40px">
            <v-btn @click="modify" style="background: #ffb319; color: white" class="Dialog_Btn"
              >확인</v-btn
            >
            <v-btn
              @click="modifyDialog = false"
              style="background: #9f9f9f; color: white"
              class="Dialog_Btn"
              >취소</v-btn
            >
          </div>
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
import Swal from "sweetalert2";

export default {
  data() {
    return {
      editMode: false,
      deleteDialog: false,
      modifyDialog: false,
      noteList1: [],
      noteList2: [],
      selected: [],
      selectDiary: "",
      diaryTitleList: [],
      diaryList: [],
    };
  },
  computed: {
    loginUser() {
            return this.$store.getters.getLoginUser
    },
    currentDiary() {
        return this.$store.getters.getCurrentDiary;
    },
    ...mapGetters(['getAllFonts']),
  },
  methods: {
    change(editMode) {
      this.editMode = !editMode;
    },
    deleteMode() {
      this.deleteDialog = true;
    },
    modifyMode() {
      this.modifyDialog = true;
    },
    goDetailNote(note){
      this.$store.commit('setNoteContent', note) // mutaion 호출 ('뮤테이션 이름, 매개변수)
      this.$router.push("/diary");
    },
    modify(){
      var changeId = ''
      // var changeTitle = ''
      for(let i=0;i<this.diaryList.length;i++){
        if(this.selectDiary == this.diaryList[i].diaryTitle){
          // changeTitle = diaryList[i].diaryTitle
          changeId = this.diaryList[i].id
        }
      }
      var list = []
      for(let i=0;i<this.selected.length;i++){
        if(this.selected[i]){
          list.push(this.selected[i])
        }
      }
      const moveList = {
        id: changeId,
        list: list
      }
      this.$store.dispatch('moveNote', moveList)
        .then(() => {
          this.$store.dispatch("getDiaryContent", this.currentDiary.id)
            .then((res) => {
              console.log(res.data)
              this.noteList1 = []
              this.noteList2 = []
              for(let i=0; i<res.data.note.length; i++){
                if(i<6){
                  this.noteList1.push(res.data.note[i])
                }
                else if(i<12){
                  this.noteList2.push(res.data.note[i])
                }
              }
              Swal.fire({
                icon: "success",
                title:
                  '<span style="font-size:25px;">이동이 완료되었습니다.</span>',
                confirmButtonColor: "#b0da9b",
                confirmButtonText: '<span style="font-size:18px;">확인</span>',
              });
              this.modifyDialog = false;
              this.editMode = false
            })
        })
    },
    deleteItems(){
      for(let i=0;i<this.selected.length;i++){
        if(this.selected[i]){
          this.$store.dispatch('deleteNote', this.selected[i])
            .then(() => {
              this.$store.dispatch("getDiaryContent", this.currentDiary.id)
                .then((res) => {
                  console.log(res.data)
                  this.noteList1 = []
                  this.noteList2 = []
                  for(let i=0; i<res.data.note.length; i++){
                    if(i<6){
                      this.noteList1.push(res.data.note[i])
                    }
                    else if(i<12){
                      this.noteList2.push(res.data.note[i])
                    }
                  }
                  Swal.fire({
                    icon: "success",
                    title:
                      '<span style="font-size:25px;">삭제가 완료되었습니다.</span>',
                    confirmButtonColor: "#b0da9b",
                    confirmButtonText: '<span style="font-size:18px;">확인</span>',
                  });
                  this.deleteDialog = false;
                  this.editMode = false
                })
            })
        }
      }
    }
  },
  created() {
    this.$store.dispatch("getDiaryContent", this.currentDiary.id)
        .then((res) => {
          console.log(res.data.note)
          for(let i=0; i<res.data.note.length; i++){
            if(i<6){
              this.noteList1.push(res.data.note[i])
            }
            else if(i<12){
              this.noteList2.push(res.data.note[i])
            }
          }
        })
    
    this.$store.dispatch("diaryGet").then((res) => {
        const tmp = []
        for(let i=0;i<res.data.length;i++) {
          if(this.currentDiary.id == res.data[i].id){
            this.selectDiary = res.data[i].diaryTitle
          }
          tmp[i] = res.data[i].diaryTitle
        }
        this.diaryTitleList = tmp
        this.diaryList = res.data
      });
  }
};
</script>

<style lang="scss" scoped>
#Line{
    height:590px;
    border: 1px solid #e7e7e7;
    position:absolute;
    top:71px;
    left:461px;
}
#List_Left{
    width:443px;
    height:610px;
    display:inline-block;
    padding:36px;
    overflow: hidden;
}
#List_Right{
    width:443px;
    height:610px;
    display:inline-block;
    padding:36px;
    overflow: hidden;
}
.List_Header{
    float:right;
}
.Header_txt{
    cursor: pointer;
    font-size:14px; 
    color:#9f9f9f;
    position:relative;
    top:-10px;
}
.Img_Box{
    width:378px;
    height:505px;
    margin:0 auto;
    margin-top:22px;
    overflow: hidden;
}
.Img_Item{
    display: inline-block;
    width:182px;
    height:163px;
    // border:1px solid #929292;
    background:#f7f7f7;
    position: relative;
    margin-bottom:8px;
    margin-right:6px;
    cursor:pointer;
}
.check{
    width:25px;
    height:25px;
    position:absolute;
    top:-12px;
    left:8px;
    z-index: 100;
}
.Img{
    width:182px;
    height:163px;
}
.Img2{
    width:182px;
    height:163px;
    opacity: 0.3;
    z-index: -1;  
}
.Dialog{
    height:180px;
}
.Dialog_Header{
    height:40px;
}
.Dialog_Close{
    float:right;
    margin-top:5px;
    margin-right:5px;
}
.Dialog_Text{
    width:131px;
    height:38px;
    margin:0 auto;
    margin-top:10px;
    font-size:16px;
    text-align: center;
}
.Dialog_Combo{
    width:164px;
    height:38px;
    margin:0 auto;
    font-size:16px;
    text-align: center;
    position:relative;
    top:-10px;
}
.Dialog_Btn_Box{
    width:164px;
    height:32px;
    display:flex;
    justify-content: space-between;
    margin:0 auto;
    margin-top:30px;
}
.Dialog_Btn{
    width:76px;
    height:32px;
}
</style>