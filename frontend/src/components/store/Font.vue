<template>
  <div>
    <div
    v-for="font in fontList"
    :key="font.id"
    class="Font_Item"
    >
      <div class="Font_Header">
        <span class="font-name" :style="{ 'font-family': font.fontUrl }">{{font.fontName}}</span>
        <span class="Font_Price">{{font.fontPrice}}장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription" :style="{ 'font-family': font.fontUrl }">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn @click="[dialog = true, sendInfo(font.fontPrice, font.id)]" class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>

      
    </div>

    <!-- <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn @click="dialog = true" class="Font_Btn" color="#FFB319" outlined
          >구매</v-btn
        >
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name" style="font-family: 'KOTRAHOPE">코트라 희망체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription" style="font-family: 'KOTRAHOPE">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name" style="font-family: 'InkLipquid">잉크립퀴드체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription" style="font-family: 'InkLipquid">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name" style="font-family: 'KyoboHand">교보 손글씨체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription" style="font-family: 'KyoboHand">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name" style="font-family: 'WandohopeB" >완도 희망체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription" style="font-family: 'WandohopeB">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div>
    <div class="Font_Item">
      <div class="Font_Header">
        <span class="font-name">나눔고딕체</span>
        <span class="Font_Price">100장</span>
      </div>
      <div class="Font_Content">
        <div class="Font_Discription">
          즐거운 일기 쓰기! papers에서 매일매일 추억을 쌓아보아요.
        </div>
        <v-btn class="Font_Btn" color="#FFB319" outlined>구매</v-btn>
      </div>
    </div> -->
    <!-- Dialog -->
    <v-dialog v-model="dialog" persistent max-width="286">
      <v-card id="Dialog">
        <div id="Dialog_Header">
          <v-icon
            @click="dialog = false"
            id="Dialog_Close"
            style="font-size: 1.5em"
            >close</v-icon
          >
        </div>
        <div id="Dialog_Content">
          <div id="Dialog_Text">
            종이 <span style="color: #ffb319">{{buyFontPrice}}장</span>을 소비해
            구매하시겠습니까 ?
          </div>
          <div id="Dialog_Btn_Box">
            <v-btn style="background: #ffb319; color: white" class="Dialog_Btn" @click="buyFont(buyFontId)"
              >구매</v-btn
            >
            <v-btn
              @click="dialog = false"
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
import Swal from "sweetalert2";

export default {
  data() {
    return {
      dialog: false,
      fontList: [],
      buyFontPrice: null,
      buyFontId: null
    };
  },
  created () {
    this.getAllFonts()
  },
  methods: {
    getAllFonts: function () {
      this.$store.dispatch("getAllFonts")
      .then((res) => {
        this.fontList = res.data
        console.log(this.fontList, '폰트리스트')
      })
    },
    sendInfo: function (price, id) {
      console.log(price, '클릭한 폰트 가격')
      console.log(id, '클릭한 폰트 아이디')
      this.buyFontPrice = price
      this.buyFontId = id
    },
    buyFont(fontId) {
      console.log(fontId, '구매하려는 폰트 아이디')
      this.$store.dispatch("buyFont", fontId)
      .then((res) => {
        console.log(res)
        Swal.fire({
          icon: "success",
          title:
            '<span style="font-size:25px;">성공적으로 구매되었습니다.</span>',
          confirmButtonColor: "#b0da9b",
          confirmButtonText: '<span style="font-size:18px;">확인</span>',
        });
      })
    }
  },
};
</script>

<style scoped>
.Font_Item {
  display: inline-block;
  width: 286px;
  height: 180px;
  margin-bottom: 32px;
  margin-left: 32px;
  border-radius: 8px;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
  overflow: hidden;
}
.Font_Header {
  height: 50px;
  border-bottom: 1px solid #e7e7e7;
  line-height: 50px;
}
/* .Font_Content {
  height: 129px;
} */
.font-name {
  margin-left: 16px;
  font-size: 16px;
  font-weight: 500;
}
.Font_Price {
  font-size: 16px;
  float: right;
  margin-right: 16px;
  color: #ffb319;
  font-weight: 600;
}
.Font_Discription {
  /* width: 243px; */
  height: 44px;
  margin: 16px 16px 0 16px;
  font-size: 15px;
  color: #585858;
  /* background-color: aquamarine; */
}
.Font_Btn {
  width: 70px;
  height: 32px;
  margin-top: 21px;
  margin-right: 16px;
  float: right;
  font-size: 15px;
  /* color: #585858; */
}
#Dialog {
  height: 180px;
}
#Dialog_Header {
  height: 40px;
}
#Dialog_Close {
  float: right;
  margin-top: 5px;
  margin-right: 5px;
}
#Dialog_Text {
  width: 131px;
  height: 38px;
  margin: 0 auto;
  margin-top: 5px;
  font-size: 16px;
  text-align: center;
}
#Dialog_Btn_Box {
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
  color: #585858;
}
</style>