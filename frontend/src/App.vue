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
  name: "App",

  data: () => ({
    //
  }),
};
</script>

<style>
@import url('https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/variable/pretendardvariable.css');

/* @font-face {
  font-family: "Pretendard-Regular";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff")
    format("woff");
  font-weight: 400;
  font-style: normal;
}
@font-face {
  font-family: "Pretendard-Medium";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Medium.woff")
    format("woff");
  font-weight: 500;
  font-style: normal;
}
@font-face {
  font-family: "Pretendard-SemiBold";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-SemiBold.woff")
    format("woff");
  font-weight: 600;
  font-style: normal;
} */
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
