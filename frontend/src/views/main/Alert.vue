<template>
  <div id="Alert_Container">
    <div class="Alert_List">
      <p class="Alert_Day">오늘</p>
      <!-- //////// 여기가 제가 새로 짠 코드 //////// -->
      <div id="alert-list-item">
        <div class="alert-content">
          <div class="alert-profile">
            <img src="../../assets/image/user.png" style="width:100%; height:100%" />
          </div>
          <div class="alert-msg">
            <span>싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.</span>
          </div>
        </div>
        <div class="alert-time">
          <span>오후 7시 21분</span>
        </div>
      </div>
      <div id="alert-list-item">
        <div class="alert-content">
          <div class="alert-profile">
            <img src="../../assets/image/user.png" style="width:100%; height:100%" />
          </div>
          <div class="alert-msg">
            <span>싸피님이 '여행 기록 일기장'에 일기를 작성했습니다. 싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.</span>
          </div>
        </div>
        <div class="alert-time">
          <span>오후 12시 49분</span>
        </div>
      </div>
      <!-- //////////////////////////////////////// -->

      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="item-time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다. 싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="item-time">오후 12시 49분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="item-time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="item-time">오후 10시 48분</div>
      </div>      
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#Alert_Container {
  padding-top: 44px;
  height: 100vh;
  /* overflow: auto; */
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#Alert_Container::-webkit-scrollbar {
  display: none;
}
.Alert_List {
  width: 604px;
  margin: 0 auto;
}
.Alert_Day {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 2px;
}

/* 여기가 제가 새로 짠 코드 */
#alert-list-item {
  display: flex;
  justify-content: space-between;  
  width: 100%;
  height: 74px;
  margin-top: 10px;
  padding: 15px 22px 15px 22px;
  border-radius: 12px;
  background-color: #fff;
  box-shadow: 2px 2px 10px rgba(166, 166, 168, 0.25);
}
.alert-content {
  display: flex;
  align-items: center;
  background-color: #bbb;
  font-size: 15px;
}
.alert-profile {
  max-width: 44px;
  max-height: 44px;
  display: inline-block;
  background-color: aquamarine;
}
.alert-msg {
  margin-left: 22px;
  max-width: 390px;
  background-color: lemonchiffon;
}
.alert-time {  
  display: flex;
  align-items: center;
  margin-left: 22px;
  font-size: 13px;
  background-color: powderblue;
}
.alert-time span {  
  color: #9f9f9f;
}

/* 아래부터가 다시 지훈님 코드 */

.Alert_Item {
  width: 604px;
  height: 75px;
  border-radius: 12px;
  box-shadow: 2px 2px 10px rgba(166, 166, 168, 0.25);
  margin-top: 10px;
  line-height: 74px;
  background-color: #fff;
}
.Item_Profile {
  height: 75px;
  width: 100px;
  display: inline-block;
  overflow: hidden;
  background-color: #bbb;
}
.Profile_img {
  width: 44px;
  height: 44px;
  margin-left: 26px;
  margin-top: 15px;  
  background-color: aquamarine;
}
.Item_Content {
  height: 74px;
  width: 398px;
  display: inline-block;
  overflow: hidden;
  font-size: 15px;
  background-color: lemonchiffon;
}
.item-time {
  display: inline-block;
  overflow: hidden;
  font-size: 13px;
  color: #9f9f9f;
  background-color: powderblue;
}
.item-time span {
  color: #9f9f9f;
}
</style>