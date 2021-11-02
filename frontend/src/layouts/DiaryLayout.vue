<template>
    <div id="Diary_Container">
        <!-- Header -->
        <Header></Header>
        <div style="height:7.5vh" />
        <router-view />
    </div>
</template>

<script>
import Header from '../components/Header.vue'
export default {
    components:{
        Header
    }
}
</script>

<style scoped>
#Diary_Container{ 
    border-right:1px solid lightgrey;
    border-left:1px solid lightgrey;
    margin:0 auto;
    width:1272px;
    height:100%;
}
</style>