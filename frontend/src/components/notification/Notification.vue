<template>
  <div align="center" class="noti-container" >
    <div align="right" class="noti-top">
      <v-icon class="close-noti" style="font-size: 1em" @click="closeNotification()">close</v-icon>
    </div>
    <div class="noti-body" @click="notificationClick()">
      <div class="left-area">
        <img :src="getNotificationUserImage" />
      </div>
      <div align="left" class="right-area">
        {{getNotificationMessage}}
      </div>
    </div>
    
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {}
  },
  computed: {
    ...mapGetters(["getNotificationMessage", "getNotificationUserImage"]),
  },
  methods: {
    closeNotification () {
      // this.$emit('change-notification-state', false)
      this.$store.commit('setNotificationState', false)
    },
    notificationClick () {
      // this.$router.push('alert')
      this.$router.push('/main/alert').catch(() => {})
      // this.$emit('change-notification-state', false)
      this.$store.commit('setNotificationState', false)
    }
  },
  mounted() {
    window.onresize = function (e) {
      console.log(e)
      const el = document.querySelector('.noti-container')
      if (el) {
        el.style.left = (window.innerWidth - 350 ) + 'px'
        el.style.top = (window.innerHeight - 200) + 'px'
      }
    }
  },
};
</script>

<style scoped>
.left-area {
  float: left;
  width: 30%;
  padding: 25px;
}
.left-area > img {
  width: 50px;
  height: 50px;
  border-radius: 25px;
}
.right-area {
  float: right;
  width: 70%;
  padding: 25px 12px;
  height: 100%;
  line-height: 20px;
}
.noti-container {
  width: 300px;
  height: 160px;
  /* position: fixed; */
  position: fixed;
  /* left: 1580px;
  top: 750px; */
  left: 80vw;
  top: 80vh;
  border-radius: 12px;
  background-color: #fff;
  box-shadow: 4px 4px 10px rgba(43, 43, 49, 0.25);
  z-index: 100;
}
.noti-top {
  height: 10%;
  margin: 10px 20px;
}
.noti-body {
  height: 90%;
  line-height: 90%;
}
.noti-body:hover {
  cursor: pointer;
}
.close-noti {
  cursor: pointer;
}
</style>