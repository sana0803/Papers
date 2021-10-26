<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
// import About from './views/About.vue';

export default {
  // components: { About },
  name: 'App',

  data: () => ({
    //
  }),
};
</script>

<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
@font-face {
    font-family: 'Cafe24Syongsyong';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Cafe24Syongsyong.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
span,div{
    font-family: 'Pretendard-Regular';
}
</style>
