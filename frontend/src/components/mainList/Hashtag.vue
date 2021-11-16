<template>
  <div>
    <div id="Hashtag_Search">
      <v-text-field
        class="Search_Input"
        color="#FFB319"
        label="해시태그를 검색해보세요."
        v-model="search"
        v-on:keyup="searchHashtag"
        v-on:focus="check"
      ></v-text-field>
      <v-btn id="Search_Btn" icon @click="searchHashtag">
        <v-icon style="font-size: 2.8em">search</v-icon>
      </v-btn>
    </div>
    <div v-if="listVisible" id="Hastag_List">
      <div @click="clickHashtag(hashtag)" v-for="(hashtag, idx) in hashtagList" :key="idx" class="Hashtag_Item">#{{ hashtag }}</div>
    </div>
    <div
      v-for="note in noteList"
      :key="note.id"
      class="note_Item"
      @click="goDetailNote(note)"
    >
      <div class="note_ImgBox">
        <v-img class="note_Img" :src="note.noteMediaList[0]" />
      </div>
      <div class="note-under">
        <span class="note_Name">{{ note.noteTitle }}</span>
        <span class="note_Day">{{ note.noteCreatedDate }}</span>
      </div>
    </div>
    <div v-if="noteList.length === 0" id="hashtag-txt">
      <span style="color:#929292;">검색된 결과가 없습니다. 해시태그를 검색해주세요 !</span>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      search : "",
      hashtagList: [],
      listVisible: true,
      noteList: [],
    }
  },
  methods: {
    searchHashtag () {
      if(this.search==''){
        this.listVisible = true
        this.noteList = []
      }

      this.$store.dispatch('searchHashtag', this.search).then((res) => {
        // if(res.data.length != 0){
        //   this.listVisible = false
        // }else{
        //   this.listVisible = true
        // }
        this.noteList = res.data.reverse()
      })
    },
    clickHashtag(hashtag) {
      this.$store.dispatch('searchHashtag', hashtag).then((res) => {
        // if(res.data.length != 0){
        //   this.listVisible = false
        // }else{
        //   this.listVisible = true
        // }
        this.noteList = res.data.reverse()
      })
    },
    check() {
      if(this.search==''){
        this.listVisible = true
        this.noteList = []
      }
    },
    goDetailNote(note) {
      this.$store.dispatch("getDiaryContent", note.diaryId).then((res) => {
        this.$store.commit('setCurrentDiary', res.data)
        this.$store.commit('setNoteContent', note) // mutaion 호출 ('뮤테이션 이름, 매개변수)
        // this.$router.push({name: 'diary', query: {diaryId: '1', noteId: '2'}})
        this.$router.push("/diary");
      })
    }
  },
  created() {
    this.$store.dispatch('getHashtag')
      .then((res) => {
        this.hashtagList = res.data
        console.log(this.hashtagList)
      })
  }
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
  height:90px;
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
.note_Item {
  display: inline-block;
  width: 286px;
  height: 432px;
  margin-bottom: 31px;
  margin-left: 31px;
}
.note_ImgBox {
  width: 286px;
  height: 394px;
  background: #fff;
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.35);
  cursor: pointer;
  overflow:hidden;
  margin:0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
}
.note_Img{
  /* width:100%;
  height:100%; */
  object-fit:contain;
}
.note_Name {
  font-size: 15px;
  max-width:190px;
}
.note_Day{
  font-size: 15px;
  color:#929292;
}
.note-under{
  margin-top:12px;
  display: flex;
  justify-content: space-between;
  align-items:center;
}
#hashtag-txt{
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top:75px;
  font-size:20px;
  height:200px;
}
</style>