<template>
  <div id="alert-container">
    <div class="Alert_List">
      <p class="Alert_Day">오늘</p>      
      <div class="alert-list-item" v-for="notification in getNotifications" :key="notification.id" @click="readAlert(notification.id, notification.diaryId, notification.noteId)">
        <div class="alert-content">
          <span v-if="notification.notificationRead" class="alert-read-empty"></span>
          <span v-else class="alert-read"></span>
          <div class="alert-profile">
            <img :src="notification.senderImageUrl" style="width:45px; height:45px; border-radius: 25px;" />
          </div>
          <div class="alert-msg">
            <span>{{notification.notificationContent}}</span>
          </div>
        </div>
        <div class="alert-time">
          <span>{{notification.notificationDate}}</span>
        </div>
      </div>      
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
    };
  },
  computed: {
    ...mapGetters(["getNotifications"]),
  },
  methods: {
    readAlert(notificationId, diaryId, noteId) {
      this.$store.dispatch("readNotification", notificationId) // 읽음 처리 put method
      .then((res) => {
        console.log(res.data)
        if (noteId === 0) { // 노트 아이디가 없는 것은 일기장 생성에 대한 알림
          this.$router.push({name: 'main', query: {diaryId: diaryId, mainListMode: 'diary'}}).catch(() => {})
          return
        }

        this.$store.dispatch('getDiaryContent', diaryId)
          .then((res) => {
            this.$store.commit('setCurrentDiary', res.data)
            this.$router.push({name: 'diary', query: {diaryId: diaryId, noteId: noteId}}).catch(() => {})
          })
      }) 
    }
  },
  mounted() {
    this.$store.dispatch("getNotifications")
      .then((response) => {
        this.$store.commit('setNotifications', response.data)
        console.log(response.data)
      }) 
  }
};
</script>

<style scoped>
#alert-container {
  padding-top: 44px;
  height: 100%;
  /* overflow: auto; */
  -ms-overflow-style: none;
  scrollbar-width: none;
}
#alert-container::-webkit-scrollbar {
  display: none;
}
.Alert_List {
  width: 604px;
  margin: 0 auto;
  padding-bottom: 50px;
}
.Alert_Day {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 2px;
}
.alert-list-item {
  display: flex;
  justify-content: space-between;  
  width: 100%;
  height: 74px;
  margin-top: 10px;
  padding: 15px 22px;
  border-radius: 12px;
  background-color: #fff;
  box-shadow: 2px 2px 10px rgba(166, 166, 168, 0.25);
  cursor: pointer;
}
.alert-list-item:hover {
  transition: .25s;
  background-color: #f5f5f5;
  box-shadow: 2px 2px 10px rgba(166, 166, 168, 0.6);
}
.alert-content {
  display: flex;
  align-items: center;
  font-size: 15px;
}
.alert-profile {
  max-width: 44px;
  max-height: 44px;
  display: inline-block;
}
.alert-msg {
  margin-left: 22px;
  max-width: 390px;
}
.alert-time {  
  display: flex;
  align-items: center;
  margin-left: 22px;
  font-size: 13px;
}
.alert-time span {  
  color: #9f9f9f;
}
.alert-read-empty {
  width: 8px;
  height: 8px;
}
.alert-read {
  background-color: #FFB319;
  border-radius: 20px;
  width: 8px;
  height: 8px;
  margin-bottom: 40px;
}
</style>