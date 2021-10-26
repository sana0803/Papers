<template>
    <div id="Main_Container">
        <!-- Header -->
        <Header></Header>
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
#Main_Container{ 
    border-right:1px solid lightgrey;
    border-left:1px solid lightgrey;
    margin:0 auto;
    width:1272px;
    height:100vh;
}
</style>