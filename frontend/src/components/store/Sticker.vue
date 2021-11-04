<template>
  <div>
    <div @click="dialog = true" class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
      </div>
    </div>
    <div class="Sticker_Item">
      <div class="Sticker_img"></div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">고양이 스티커 패키지</span>
        <span class="Sticker_Price">50장</span>
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
            <div id="Dialog_img"></div>
            <div id="Dialog_Name">
              <div id="Name_Author">작가명 아무개</div>
              <div id="Name_Name">고양이 스티커 패키지</div>
              <div id="Name_Price">50장</div>
              <div id="Name_Btn_Box">
                <v-btn
                  style="background: #ffb319; color: white"
                  class="Name_Btn"
                  >구매</v-btn
                >
                <v-btn
                  @click="dialog = false"
                  style="background: #9f9f9f; color: white"
                  class="Name_Btn"
                  >취소</v-btn
                >
              </div>
            </div>
          </div>
          <div id="Dialog_List">
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
            <div class="Dialog_Item"></div>
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
      dialog: false,
    };
  },
};
</script>

<style scoped>
.Sticker_Item {
  display: inline-block;
  width: 284px;
  height: 424px;
  margin-bottom: 21px;
  margin-left: 31px;
}
.Sticker_Item:hover {
  opacity: 0.7;
}
.Sticker_img {
  width: 284px;
  height: 394px;
  background: rgb(199, 193, 188);
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
  cursor: pointer;
}
.Sticker_Title {
  margin-top: 10px;
  font-size: 15px;
}
.Sticker_Price {
  float: right;
  color: #ffb319;
}
#Dialog {
  height: 610px;
}
#Dialog_Header {
  height: 42px;
  border-bottom: 1px solid #d7d7d7;
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
  width: 80px;
  height: 80px;
  border-radius: 50px;
  background: #fae7cb;
  margin-right: 10px;
  margin-left: 10px;
}
#Dialog_img {
  display: inline-block;
  width: 118px;
  height: 118px;
  border-radius: 100px;
  background: #b8dfd8;
}
#Dialog_Name {
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
}
</style>