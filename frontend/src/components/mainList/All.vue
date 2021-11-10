<template>
  <div>
    <!-- <div v-for="note in noteList" :key="note.id" class="MainList_Item">일기 하나임</div> -->
    <div
      v-for="note in noteList"
      :key="note.id"
      @click="detailNote(note)"
      class="note_Item"
    >    
      <div class="note_ImgBox">
        <v-img class="note_Img" :src="note.noteMediaList[0]" />
      </div>
      <div class="note-under">
        <span class="note_Name">{{ note.noteTitle }}</span>
        <span class="note_Day">{{ note.noteCreatedDate }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return{
      noteList: []
    }
  },
  methods: {
    detailNote(note) {
      // this.$store.commit('setDiaryContent', diaryContent)      
      // this.$router.push("/diary");
      console.log(note)
    }
  },
  created() {
    this.$store.dispatch("noteGet").then((res) => {
      this.noteList = res.data.reverse();
      console.log(this.noteList)
    });
    // this.$store.dispatch("getDiaryContent", this.currentDiary.id)
    //   .then((res) => {
    //     console.log(res.data)        
    //     this.noteList = res.data.note.reverse();      
    //   })
  },
};
</script>

<style scoped>
.note_Item {
  display: inline-block;
  width: 284px;
  height: 432px;
  margin-bottom: 31px;
  margin-left: 31px;
}
.note_ImgBox {
  width: 284px;
  height: 394px;
  background: rgb(185, 228, 161);
  box-shadow: 2px 3px 12px 2px rgba(35, 35, 35, 0.25);
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
</style>