<template>
  <div id="Write_Container">
    <write-side />
    <div id="Write_Main">
      <write-content-2 />
    </div>
  </div>
</template>

<script>
import WriteSide from "../../components/write/WriteSide.vue";
import WriteContent2 from "../../components/write/WriteContent2.vue";

export default {
  components: {
    WriteSide,
    WriteContent2,
  },
};
</script>

<style lang="scss" scoped>
#Write_Container {
  height: 100%;
}
#Write_Main {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>