<template>
  <div>
    <div class="Sticker_Item" v-for="stickerPackage in viewList" :key="stickerPackage.id" @click="dialogOn(stickerPackage)">
      <div class="sticker-check" v-if="stickerPackage.owned">보유중</div>
      <div class="Sticker_img">
        <img :src="stickerPackage.stickerList[0].stickerUrl" style="width: 80%; margin-top: 10px; margin-left: 10%; margin-top: 30%;"/>
      </div>
      <div class="Sticker_Title">
        <span class="Sticker_Name">{{ stickerPackage.stickerPackageName }}</span>
        <span class="Sticker_Price">{{ stickerPackage.stickerPackagePrice }}장</span>
      </div>
    </div>

    <!-- 스티커 페이지네이션 -->
    <div id="diary-pagination">
      <v-pagination
        style="margin-bottom:30px;"
        v-model="page"
        :length="Math.ceil(stickerPackageList.length/6)"
        @input="change"
        circle
        color="#FFB300"
      ></v-pagination>
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
              <img :src="stickerList[0].stickerUrl" style="width: 80%; height: 80%; margin-top: 15px; margin-left: 15px;"/>
            </div>
            <div id="Dialog_Name">
              <div id="Name_Author">Papers</div>
              <div id="Name_Name">{{ stickerPackage.stickerPackageName }}</div>
              <div id="Name_Price">{{ stickerPackage.stickerPackagePrice }}장</div>
              <div id="Name_Btn_Box">
                <v-btn
                  style="background: #ffb319; color: white"
                  class="Name_Btn"
                  @click="purchageStickerPackage(stickerPackage)"
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
            <div class="Dialog_Item" v-for="sticker in stickerList" :key="sticker.id">
              <img :src="sticker.stickerUrl" style="width: 80%; height: 80%; margin-top: 10px; margin-left: 10px;"/>
            </div>
          </div>
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import Swal from "sweetalert2";

export default {
  data() {
    return {
      page: 1,
      viewList: [],
      dialog: false,
      stickerPackageList: [],
      stickerList: [{
        id: 1,
        stickerUrl: '',
      }],
      stickerPackage: '',
      loginUser: {
        userMileage: '',
      }
    };
  },
  created () {
    this.$store.dispatch('getStoreStickerList').then((res) => {
      this.stickerPackageList = res.data;
      for(let i=0;i<6;i++){
          if(this.stickerPackageList.length==i) 
              break
            this.viewList.push(this.stickerPackageList[i])
        }
    })
    this.loginUser = this.$store.getters['getLoginUser'];
  },
  methods:{
    change(num) {
      var temp = 0
      for(let i=1;i<this.stickerPackageList.length;i++){
        if(i==num){
          this.viewList = []
          for(let i=temp;i<temp+6;i++){
            if(this.stickerPackageList.length==i)
              break
            this.viewList.push(this.stickerPackageList[i])
          }
        }
        temp+=6
      }
    },
    dialogOn(stickerPackage) {
      this.stickerPackage = stickerPackage;
      this.stickerList = stickerPackage.stickerList;
      this.dialog = true;
    },
    purchageStickerPackage(stickerPackage) {
      this.$store.dispatch('purchaseStickerPackage', stickerPackage.id).then(() => {
        this.loginUser.userMileage -= stickerPackage.stickerPackagePrice;
        this.$store.commit('setLoginUser', this.loginUser);
        
        this.$store.dispatch('getStoreStickerList').then((res) => {
          this.stickerPackageList = res.data;
          this.change(this.page)

          Swal.fire({
            icon: "success",
            title:
              '<span style="font-size:25px;">성공적으로 구매되었습니다.</span>',
            confirmButtonColor: "#b0da9b",
            confirmButtonText: '<span style="font-size:18px;">확인</span>',
          });
          this.dialog = false
        })
      })
      .catch(() => {
        alert('마일리지가 부족하거나 이미 소유중입니다');
        this.dialog = false;
      })
    }
  }
}
</script>

<style scoped>
.Sticker_Item {
  display: inline-block;
  width: 286px;
  margin-bottom: 32px;
  margin-left: 32px;
  position: relative;
}
.Sticker_Item:hover {
  opacity: 0.7;
}
.sticker-check{
  position: absolute;
  background: #ffb319;
  color: white;
  font-weight: 300;
  text-align: center;
  line-height: 30px;
  width: 60px;
  height: 30px;
  top: 12px;
  left: 12px;
  border-radius: 4px;
}
.Sticker_img {
  width: 286px;
  height: 394px;
  background: #fff;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.35);
  overflow: hidden;
  cursor: pointer;
}
.Sticker_Title {
  margin-top: 19px;
  font-size: 15px;
}
.Sticker_Price {
  float: right;
  color: #ffb319;
  font-weight: 600;
  font-size: 15px;
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
  /* background-color: lightpink; */
}
#Dialog_Title {
  height: 118px;
  /* background-color: #eee; */
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
  /* background-color: lemonchiffon; */
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
}
#Dialog_Name {
  /* width:200px; */
  height: 118px;
  margin-left: 61px;
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