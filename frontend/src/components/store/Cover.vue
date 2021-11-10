<template>
  <div>
    <div v-for="cover in coverList" :key="cover.id" class="MainList_Item"  @click="dialogOn(cover)">
        <div class="cover-check" v-if="cover.owned">보유중</div>
        <img :src="cover.coverUrl" style="width: 100%; height: 100%;"/>
    </div>

    <v-dialog v-model="dialog" persistent max-width="600">
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
              <img :src ="cover.coverUrl" style="width: 100%; height: 100%;"/>
            </div>
            <div id="Dialog_Name">
              <div id="Name_Author">작가명 아무개</div>
              <div id="Name_Name">{{ cover.coverName }}</div>
              <div id="Name_Price">{{ cover.coverPrice }}장</div>
              <div id="Name_Btn_Box">
                <v-btn
                  style="background: #ffb319; color: white"
                  class="Name_Btn"
                  @click="purchaseDiaryCover(cover)"
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
      cover: '',
      coverList: [],
    }
  },
  created() {
    this.$store.dispatch('getDiaryCoverList').then((res) => {
      console.log('hi')
      console.log(res.data);
      this.coverList = res.data;      
    })
  },
  methods: {
    dialogOn(cover) {
      this.cover = cover;
      this.dialog = true;
    },
    purchaseDiaryCover(cover) {
      this.$store.dispatch('purchaseDiaryCover', cover.id).then(() => {
        const loginUser = this.$store.getters['getLoginUser'];
        loginUser.userMileage -= cover.coverPrice;
        this.$store.commit('setLoginUser', loginUser);
        this.dialog = false;
        this.$router.go();
      })
      .catch(() => {
        alert('이미 구매하셨거나 마일리지가 부족합니다.')
      })
    }
  }
};
</script>

<style scoped>
.MainList_Item {
  display: inline-block;
  width: 284px;
  height: 394px;
  margin-bottom: 31px;
  margin-left: 31px;
  background: peachpuff;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
  overflow: hidden;
  position:relative;
}
#Dialog {
  width: 650px;
  height: 500px;
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
  height: 458px;
  padding: 34px 46px;
}
#Dialog_Title {
  height: 375px;
  vertical-align: center;
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
}
#Dialog_img {
  display: inline-block;
  width: 50%;
  height: 100%;
  border-radius: 100px;
  background: #b8dfd8;
}
#Dialog_Name {
  display: inline-block;
  /* width:200px; */
  width: 37%;
  height: 118px;
  margin-left: 61px;
  overflow: hidden;
  float: right;
  margin-top: 25%;
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
.cover-check{
  position:absolute;
  background: #ffb319;
  color:white;
  font-weight:300;
  text-align: center;
  line-height:30px;
  width:50px;
  height:30px;
  top:10px;
  left:10px;
}
</style>