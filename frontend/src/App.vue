<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
    <Notification></Notification>
  </v-app>
</template>

<script>
// import About from './views/About.vue';
import Notification from "../src/components/notification/Notification.vue"
export default {
  // components: { About },
  name: "App",
  components: {
    Notification,
  },
  data: () => ({
    //
  }),
};
</script>

<style>
@import url('https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/variable/pretendardvariable.css');

@font-face {
  font-family: "Cafe24Syongsyong";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Cafe24Syongsyong.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
span,
p,
div,
a {
  font-family: "Pretendard Variable";
  color: #222;
  letter-spacing: -0.01em;
}
a {
  text-decoration: none;
}
</style>
