<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
    <Notification v-if="getNotificationState"></Notification>
  </v-app>
</template>

<script>
// import About from './views/About.vue';
import Notification from "../src/components/notification/Notification.vue"
import { mapGetters } from 'vuex'
export default {
  // components: { About },
  name: "App",
  data: () => ({
    showNotification: true,
    audio: 'https://papers-bucket.s3.ap-northeast-2.amazonaws.com/audio/melody.mp3',
  }),
  components: {
    Notification,
  },
  watch: {
    getNotificationState() {
      if (this.getNotificationState === true) {
        var player = new Audio(this.audio)
        player.play()
      }
    }
  },
  computed: {
    ...mapGetters(["getNotificationState"]),
  },
  methods: {
  }
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

@font-face {
  font-family: 'KOTRAHOPE';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/KOTRAHOPE.woff2') format('woff2');
  font-weight: normal;
  font-style: normal;
}

@font-face {
    font-family: 'KyoboHand';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'WandohopeB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/WandohopeB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'Y_Spotlight';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/Y_Spotlight.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'SangSangRock';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/SangSangRockOTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
  font-family: 'UhBeeSeulvely';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_five@.2.0/UhBeeSeulvely.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
    font-family: 'GowunBatang-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunBatang-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'OTWelcomeRA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/OTWelcomeRA.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'Chosunilbo_myungjo';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Chosunilbo_myungjo.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'DOSMyungjo';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_eight@1.0/DOSMyungjo.woff') format('woff');
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
