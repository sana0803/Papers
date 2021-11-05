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
  border: 1px solid #e7e7e7;
  position: absolute;
  top: 71px;
  left: 461px;
}
</style>