<template>
  <div>
    <div id="Line"></div>
  </div>
</template>

<script>
export default {
  computed: {
    currentDiary() {
      return this.$store.getters.getCurrentDiary;
    },
  },
  created() {
    console.log(this.currentDiary)
  }
};
</script>

<style lang="scss" scoped>
#Line {
  height: 590px;
  border: 1px solid #d7d7d7;
  position: absolute;
  top: 70px;
  left: 461px;
}
</style>