<template>
  <div id="Write_Container">
    냐냐냥
    <write-side />
    <div id="update-main">
      <UpdateContent />
    </div>
  </div>
</template>

<script>
import WriteSide from "../../components/write/WriteSide.vue";
import UpdateContent from "../../components/write/UpdateContent.vue";

export default {
  components: {
    WriteSide,
    UpdateContent,
  },  
};
</script>

<style lang="scss" scoped>
#Write_Container {
  height: 100%;
}
#update-main {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>