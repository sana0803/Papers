<template>
    <div id="Diary_Container">
        <!-- Header -->
        <Header></Header>
        다이어리레이아웃
        <div style="height:500px; border:1px solid red;">
            <router-view />
        </div>
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
    height:100vh;
}
</style>