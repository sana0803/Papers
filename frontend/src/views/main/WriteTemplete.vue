<template>
  <div id="Templete_Out">
    <v-btn id="Write_Btn" @click="goWrite" fab dark large color="#FFB319">
      <v-icon dark> mdi-plus </v-icon>
    </v-btn>
    <div id="Templete_Container">
      <div id="Templete_Count">전체 6개</div>
      <div id="Templete_Content">
        <div class="MainList_Item">템플릿 하나임</div>
        <div class="MainList_Item"></div>
        <div class="MainList_Item"></div>
        <div class="MainList_Item"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goWrite() {
      this.$router.push("/write");
    },
  },
};
</script>

<style lang="scss" scoped>
#Templete_Out {
  width: 1272px;
  position: relative;
}
#Write_Btn {
  position: fixed;
  left: 77.2%;
  top: 88%;
}
#Templete_Container {
  margin: 0 auto;
  width: 985px;
}
#Templete_Count {
  margin-top: 44px;
  color: #585858;
  margin-left: 30px;
  font-size: 18px;
}
#Templete_Content {
  padding-top: 5px;
  margin-top: 39px;
}
.MainList_Item {
  display: inline-block;
  width: 284px;
  height: 394px;
  margin-bottom: 31px;
  margin-left: 31px;
  background: rgb(224, 138, 156);
  box-shadow: 3px 3px 11px rgba(166, 166, 168, 0.25);
  overflow: hidden;
}
</style>