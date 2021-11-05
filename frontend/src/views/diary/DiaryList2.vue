<template>
  <div>
    <div id="Line"></div>
    <div id="List_Left">
      <div class="Img_Box">
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check">
            <v-icon style="font-size: 1.5em; color: white">check</v-icon>
          </div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
      </div>
    </div>
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
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check"></div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check"></div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check"></div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check"></div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check"></div>
          <img class="Img" src="../../assets/image/dog.jpg" />
        </div>
        <div class="Img_Item">
          <div v-if="editMode" class="Img_Check"></div>
          <img class="Img" src="../../assets/image/dog.jpg" />
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
            <v-btn style="background: #ffb319; color: white" class="Dialog_Btn"
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
            <v-combobox outlined dense style="margin-top: 10px"></v-combobox>
          </div>
          <div class="Dialog_Btn_Box" style="margin-top: 40px">
            <v-btn style="background: #ffb319; color: white" class="Dialog_Btn"
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
export default {
  data() {
    return {
      editMode: false,
      deleteDialog: false,
      modifyDialog: false,
    };
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
  },
};
</script>

<style lang="scss" scoped>
#Line {
  height: 590px;
  border: 1px solid #d7d7d7;
  position: absolute;
  top: 70px;
  left: 461px;
}
#List_Left {
  width: 443px;
  height: 610px;
  display: inline-block;
  padding: 36px;
  overflow: hidden;
}
#List_Right {
  width: 443px;
  height: 610px;
  display: inline-block;
  padding: 36px;
  overflow: hidden;
}
.List_Header {
  float: right;
}
.Header_txt {
  cursor: pointer;
  font-size: 14px;
  color: #9f9f9f;
}
.Img_Box {
  width: 378px;
  height: 505px;
  margin: 0 auto;
  margin-top: 32px;
  // display: flex;
  // flex-wrap: wrap;
  // justify-content: space-between;
  // align-items: center;
}
.Img_Item {
  display: inline-block;
  width: 182px;
  height: 163px;
  background: rgb(235, 188, 156);
  position: relative;
  margin-bottom: 8px;
  margin-right: 6px;
}
.Img_Check {
  width: 25px;
  height: 25px;
  background: #ffb319;
  border: 1px solid #ffffff;
  border-radius: 50px;
  position: absolute;
  top: 8px;
  left: 8px;
  cursor: pointer;
}
.Img {
  width: 182px;
  height: 163px;
}
.Dialog {
  height: 180px;
}
.Dialog_Header {
  height: 40px;
}
.Dialog_Close {
  float: right;
  margin-top: 5px;
  margin-right: 5px;
}
.Dialog_Text {
  width: 131px;
  height: 38px;
  margin: 0 auto;
  margin-top: 5px;
  font-size: 16px;
  text-align: center;
}
.Dialog_Combo {
  width: 164px;
  height: 38px;
  margin: 0 auto;
  font-size: 16px;
  text-align: center;
  position: relative;
  top: -10px;
}
.Dialog_Btn_Box {
  width: 164px;
  height: 32px;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  margin-top: 30px;
}
.Dialog_Btn {
  width: 76px;
  height: 32px;
}
</style>