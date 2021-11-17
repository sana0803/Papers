<template>
  <div id="Main_Container">
    <!-- Header -->
    <Header></Header>
    <div style="height: 7.5vh" />
    <router-view />
    <!-- <Notification></Notification> -->
  </div>
</template>

<script>
import Header from "../components/Header.vue";
// import Notification from "../components/notification/Notification.vue"
export default {
  components: {
    Header,
    // Notification,
  },
  created(){
    const loginCheck = localStorage.getItem('userId')
    if(loginCheck==null){
      this.$router.push('/').catch(() => {})
    }
  }
};
</script>

<style scoped>
#Main_Container {
  /* border-right: 1px solid lightgrey; */
  /* border-left: 1px solid lightgrey; */
  margin: 0 auto;
  width: 1272px;
  height: 100%;
}
</style>