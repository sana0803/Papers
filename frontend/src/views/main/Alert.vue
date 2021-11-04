<template>
  <div id="Alert_Container">
    <div class="Alert_List">
      <div class="Alert_Day">오늘</div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
      </div>
      <div class="Alert_Item">
        <div class="Item_Profile">
          <img src="../../assets/image/user.png" class="Profile_img" />
        </div>
        <div class="Item_Content">
          싸피님이 '여행 기록 일기장'에 일기를 작성했습니다.
        </div>
        <div class="Item_Time">오후 7시 21분</div>
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
  height: 92.5vh;
  overflow: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#Alert_Container::-webkit-scrollbar {
  display: none;
}
.Alert_List {
  width: 620px;
  margin: 0 auto;
}
.Alert_Day {
  font-size: 16px;
  font-weight: bold;
}
.Alert_Item {
  width: 600px;
  height: 75px;
  border-radius: 15px;
  box-shadow: 2px 2px 10px rgba(166, 166, 168, 0.25);
  margin: 0 auto;
  margin-top: 10px;
  line-height: 75px;
}
.Item_Profile {
  height: 75px;
  width: 100px;
  display: inline-block;
  overflow: hidden;
}
.Profile_img {
  width: 45px;
  height: 45px;
  margin-left: 27.5px;
  margin-top: 15px;
}
.Item_Content {
  height: 75px;
  width: 398px;
  display: inline-block;
  overflow: hidden;
  font-size: 15px;
}
.Item_Time {
  height: 75px;
  width: 100px;
  display: inline-block;
  overflow: hidden;
  font-size: 15px;
  color: grey;
}
</style>