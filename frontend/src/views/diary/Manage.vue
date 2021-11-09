<template>
  <div>
    <div id="Line"></div>
    <!-- Left -->
    <div id="Manage_Left">
      <div id="Left_Box">
        <div class="Header">멤버 목록</div>
        <div id="Member_Box">
          <div class="Member_Item">
            <div class="Member_Img">
              <img class="Member_Image" src="../../assets/image/user.png" />
              <div class="Member_Icon">
                <v-icon class="Icon" style="font-size: 1em; color: white"
                  >check</v-icon
                >
              </div>
            </div>
            <div class="Member_Name">김싸피</div>
          </div>
          <div class="Member_Item">
            <div class="Member_Img">
              <img class="Member_Image" src="../../assets/image/user.png" />
              <div class="Member_Icon2">
                <v-icon
                  @click="dialog = true"
                  class="Icon"
                  style="font-size: 1em; color: white"
                  >remove</v-icon
                >
              </div>
            </div>
            <div class="Member_Name">김싸피</div>
          </div>
          <div class="Member_Item">
            <div class="Member_Img">
              <img class="Member_Image" src="../../assets/image/user.png" />
              <div class="Member_Icon2">
                <v-icon class="Icon" style="font-size: 1em; color: white"
                  >remove</v-icon
                >
              </div>
            </div>
            <div class="Member_Name">김싸피</div>
          </div>
          <div class="Member_Item">
            <div class="Member_Img">
              <img class="Member_Image" src="../../assets/image/user.png" />
              <div class="Member_Icon2">
                <v-icon class="Icon" style="font-size: 1em; color: white"
                  >remove</v-icon
                >
              </div>
            </div>
            <div class="Member_Name">김싸피</div>
          </div>
          <div class="Member_Item">
            <div class="Member_Img">
              <img class="Member_Image" src="../../assets/image/user.png" />
              <div class="Member_Icon2">
                <v-icon class="Icon" style="font-size: 1em; color: white"
                  >remove</v-icon
                >
              </div>
            </div>
            <div class="Member_Name">김싸피</div>
          </div>
        </div>
        <div class="Header2">멤버 초대</div>
        <div id="Invite_Input">
          <v-text-field
            class="Search_Input"
            color="#FFB319"
            label="아이디 검색"
            v-model="search"
            v-on:keyup="memberSearch"
          ></v-text-field>
          <v-btn id="Search_Btn" icon>
            <v-icon @click="memberSearch" style="font-size: 2.8em">search</v-icon>
          </v-btn>
        </div>
        <div id="Search_List">
          <div v-if="memberList.length === 0" id="Invite_txt">검색결과가 없습니다.</div>
          <div v-if="memberList.length !== 0" id="member-list">
            <div v-for="(member,idx) in memberList" :key="member.userId" class="Search_Item">
              <div class="Search_Img">
                <img class="Img" :src="member.userProfile" />
              </div>
              <span class="Search_Name">{{member.userNickname}} </span>
              <span calss="Search_UserId">({{ member.userId }})</span>
              
              <!-- <div class="Search_Check"></div> -->
              <div class="Search_Check">
                <v-checkbox
                  class="check"
                  v-model="selected[idx]"
                  :key="member.userId"
                  :value="member.userId"
                ></v-checkbox>
              </div>
            </div>
          </div>
          <div v-if="memberList.length !== 0" id="invite_btn">
            <v-btn style="background: #ffb319; color: white;" class="Btn"
            >확인
            </v-btn>
          </div>
        </div>
      </div>
    </div>
    <!-- Right -->
    <div id="Manage_Right">
      <div id="Right_Box">
        <div class="Header">타이틀 변경</div>
        <div id="Title_Input">
          <v-text-field color="#FFB319" label="ㅇㅇㅇ일기장"></v-text-field>
        </div>
        <div class="Header2">커버 편집</div>
        <div class="Header3">기본 커버</div>
        <div class="Cover_Box">
          <div v-for="(item, idx) in coverList" :key="idx" class="cover-item">
            <v-img class="cover-img" :src="item.coverUrl" />
          </div>
        </div>
        <div class="Header3">내 커버</div>
        <div class="Cover_Box"></div>
        <div class="Header3">사진</div>
        <div class="Cover_Box"></div>
        <div id="Btn_Box">
          <v-btn style="background: #ffb319; color: white" class="Btn"
            >확인</v-btn
          >
          <v-btn
            @click="back"
            style="background: #9f9f9f; color: white"
            class="Btn"
            >취소</v-btn
          >
        </div>
      </div>
    </div>
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
            이 멤버를<br />
            추방하시겠습니까?
          </div>
          <div id="Btn_Box2">
            <v-btn style="background: #ffb319; color: white" class="Btn"
              >확인</v-btn
            >
            <v-btn
              @click="dialog = false"
              style="background: #9f9f9f; color: white"
              class="Btn"
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
  computed: {
    currentDiary() {
      return this.$store.getters.getCurrentDiary;
    },
  },
  data() {
    return {
      dialog: false,
      search: '',
      coverList: [],
      memberList: [],
      selected: [],
    };
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    memberSearch() {
      console.log(123)
      this.$store.dispatch('memberSearch', this.search)
        .then((res) => {
          this.memberList = res.data
          console.log(this.memberList)
          if(this.search == '') {
            this.memberList = []
          }
        })
    }
  },
  created() {
    this.$store.dispatch('getCover')
      .then((res) => {
        this.coverList = res.data
        console.log(this.coverList)
      })
  }
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
#Manage_Left {
  width: 443px;
  height: 610px;
  display: inline-block;
  padding: 36px;
  overflow: hidden;
}
#Manage_Right {
  width: 443px;
  height: 610px;
  display: inline-block;
  padding: 36px;
  overflow: hidden;
}
#Left_Box {
  width: 100%;
  height: 100%;
}
#Right_Box {
  width: 100%;
  height: 100%;
}
.Header {
  height: 22px;
  font-size: 18px;
  line-height: 22px;
  font-weight: 600;
}
#Member_Box {
  width: 105%;
  margin-top: 20px;
  height: 80px;
  // border:1px solid red;
}
.Member_Item {
  display: inline-block;
  width: 65px;
  height: 80px;
  margin-right: 12px;
}
.Member_Img {
  width: 60px;
  height: 60px;
  margin: 0 auto;
  position: relative;
}
.Member_Icon {
  width: 16px;
  height: 16px;
  background: #ffb319;
  border-radius: 50px;
  position: absolute;
  top: -3px;
  left: 50px;
  cursor: pointer;
}
.Member_Icon2 {
  width: 16px;
  height: 16px;
  background: #e41d35;
  border-radius: 50px;
  position: absolute;
  top: -3px;
  left: 50px;
  cursor: pointer;
}
.Icon {
  position: relative;
  top: -5px;
}
.Member_Image {
  border-radius: 50px;
  width: 60px;
  height: 60px;
}
.Member_Name {
  height: 20px;
  line-height: 20px;
  font-size: 15px;
  text-align: center;
}
.Header2 {
  margin-top: 32px;
  height: 22px;
  font-size: 18px;
  line-height: 22px;
  font-weight: 600;
}
#Invite_Input {
  margin-top: 20px;
}
.Search_Input {
  width: 89%;
  display: inline-block;
}
#Search_Btn {
  background: #ffb319;
  color: white;
  width: 40px;
  height: 40px;
  position: relative;
  top: -2px;
}
#Search_List {
  margin-top: 22px;
  height: 245px;
  padding: 14px;
  border: 1px solid #d7d7d7;

}
#member-list::-webkit-scrollbar {
  display: none;
}
#member-list{
  overflow: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
  height:175px;
}
#Invite_txt {
  line-height: 215px;
  text-align: center;
  font-size: 16px;
  color: #9f9f9f;
}
#Title_Input {
  margin-top: 20px;
  height: 45px;
}
.Header3 {
  margin-top: 20px;
  font-size: 15px;
  color: #585858;
}
.Cover_Box {
  height: 70px;
  border: 1px solid red;
}
#Btn_Box {
  width: 164px;
  height: 32px;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  margin-top: 23px;
}
#Btn_Box2 {
  width: 164px;
  height: 32px;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  margin-top: 30px;
}
.Btn {
  width: 76px;
  height: 32px;
  box-shadow: none;
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
.cover-item{
  display:inline-block;
  height:100%;
  width:75px;
  cursor: pointer;
  overflow:hidden;
  margin:0 auto;
}
.cover-img{
  width:100%;
  height:100%;
  object-fit:cover;
}
.Search_Item {
  height: 50px;
  line-height: 50px;
}
.Search_Img {
  float: left;
  width: 50px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.Img {
  width: 43px;
  height: 43px;
  display: inline-block;
}
.Search_Name {
  margin-left: 16px;
  font-size: 16px;
}
.Search_UserId {
  color: red;
}
.Search_Check {
  height:50px;
  float: right;
}
.check{
  position:relative;
  top:-7px;
}
#invite_btn{
  width:76px;
  margin:0 auto;
  margin-top:7px;
}
</style>