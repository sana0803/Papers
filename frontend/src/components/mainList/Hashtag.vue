<template>
  <div>
    <div id="Hashtag_Search">
      <v-text-field
        class="Search_Input"
        color="#FFB319"
        label="해시태그를 검색해보세요."
        v-model="search"
        v-on:keyup.enter="searchHashtag"
      ></v-text-field>
      <v-btn id="Search_Btn" icon @click="searchHashtag">
        <v-icon style="font-size: 2.8em">search</v-icon>
      </v-btn>
    </div>
    <div id="Hastag_List">
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#홍대</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#맛집</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#일기장</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#반려묘</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
      <div class="Hashtag_Item">#해시태그</div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      search : ""
    }
  },
  methods: {
    searchHashtag () {
      this.$store.dispatch('searchHashtag', this.search).then((res) => {
        console.log(res.data)
      })
    },
  },
};
</script>

<style scoped>
#Hashtag_Search {
  width: 392px;
  margin: 0 auto;
  /* background-color:lightblue; */
  margin-bottom: 30px;
}
.Search_Input {
  width: 85%;
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
#Hastag_List {
  text-align: center;
  width: 922px;
  margin: 0 auto;
  /* background-color: #eee; */
}
.Hashtag_Item {
  background: #f6f6f6;
  border-radius: 16px;
  height: 35px;
  line-height: 35px;
  padding: 0 11px;
  font-size: 16px;
  font-weight: 500;
  color: #585858;
  margin: 0px 12px 18px 0px;
  display: inline-block;
  cursor: pointer;
}
.Hashtag_Item:hover {
  background: rgb(212, 212, 212);
}
</style>