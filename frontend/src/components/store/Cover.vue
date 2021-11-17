<template>
  <div>
    <div
      v-for="cover in viewList"
      :key="cover.id"
      class="MainList_Item"
      @click="dialogOn(cover)"
    >
      <div class="cover-check" v-if="cover.owned">보유중</div>
      <div class="cover-img">
        <img :src="cover.coverUrl" />
      </div>
      <div class="cover-title">
        <span class="cover-name">{{ cover.coverName }}</span>
        <span class="cover-price">{{ cover.coverPrice }}장</span>
      </div>
    </div>
    <!-- 커버 페이지네이션 -->
    <div id="diary-pagination">
      <v-pagination
        style="margin-bottom: 30px"
        v-model="page"
        :length="Math.ceil(coverList.length / 6)"
        @input="change"
        circle
        color="#FFB300"
      ></v-pagination>
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
              <img :src="cover.coverUrl" style="width: 100%; height: 100%" />
            </div>
            <div id="Dialog_Name">
              <div id="Name_Author">Papers</div>
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
import Swal from "sweetalert2";

export default {
  data() {
    return {
      page: 1,
      viewList: [],
      dialog: false,
      cover: "",
      coverList: [],
    };
  },
  created() {
    this.$store.dispatch("getDiaryCoverList").then((res) => {
      this.coverList = res.data;
      console.log(this.coverList);

      for (let i = 0; i < 6; i++) {
        if (this.coverList.length == i) break;
        this.viewList.push(this.coverList[i]);
      }
    });
  },
  methods: {
    change(num) {
      var temp = 0;
      for (let i = 1; i < this.coverList.length; i++) {
        if (i == num) {
          this.viewList = [];
          for (let i = temp; i < temp + 6; i++) {
            if (this.coverList.length == i) break;
            this.viewList.push(this.coverList[i]);
          }
        }
        temp += 6;
      }
    },
    dialogOn(cover) {
      this.cover = cover;
      this.dialog = true;
    },
    purchaseDiaryCover(cover) {
      this.$store
        .dispatch("purchaseDiaryCover", cover.id)
        .then(() => {
          const loginUser = this.$store.getters["getLoginUser"];
          loginUser.userMileage -= cover.coverPrice;
          this.$store.commit("setLoginUser", loginUser);
          
          this.$store.dispatch("getDiaryCoverList").then((res) => {
            this.coverList = res.data;
            this.change(this.page)
            Swal.fire({
              icon: "success",
              title:
                '<span style="font-size:25px;">성공적으로 구매되었습니다.</span>',
              confirmButtonColor: "#b0da9b",
              confirmButtonText: '<span style="font-size:18px;">확인</span>',
            });
            this.dialog = false;
          });
          // this.$router.go();
        })
        .catch(() => {
          alert("이미 구매하셨거나 마일리지가 부족합니다.");
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.MainList_Item {
  display: inline-block;
  width: 286px;
  margin-bottom: 32px;
  margin-left: 32px;
  position: relative;
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
  // background-color: #b8dfd8;
}
#Dialog_Title {
  height: 375px;
  // background: greenyellow;
  display: flex;
  justify-content: center;
  align-items: center;
}
#Dialog_img {
  width: 50%;
  height: 100%;
  img {
    overflow: hidden;
  }
}
#Dialog_Name {
  width: 37%;
  height: 150px;
  margin-left: 61px;
  // border:1px solid red;
}
#Name_Author {
  color: #585858;
  font-size: 14px;
  margin-bottom: 7px;
}
#Name_Name {
  font-size: 21px;
  font-weight: 600;
  margin-bottom: 7px;
}
#Name_Price {
  position: relative;
  top: -3px;
  font-size: 18px;
  font-weight: 600;
  color: #ffb319;
  margin-bottom: 7px;
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
.cover-check {
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
  border-radius: 5px;
}
.cover-img {
  width: 286px;
  height: 394px;
  background: rgb(199, 193, 188);
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.35);
  overflow: hidden;
  cursor: pointer;
  img {
    width: 100%;
    height: 100%;
  }
}
.cover-title {
  margin-top: 19px;
  font-size: 15px;
}
.cover-price {
  float: right;
  color: #ffb319;
  font-weight: 600;
  font-size: 15px;
}
</style>