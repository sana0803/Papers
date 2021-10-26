<template>
    <div id="Intro_Container">
        <div id="Intro_Box">
            <router-view />
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#Intro_Container{
    display:flex;   
    border-right:1px solid lightgrey;
    border-left:1px solid lightgrey;
    margin:0 auto;
    width:1272px;
    height:100vh;
    justify-content: center;
    align-items: center;
}
#Intro_Box{
    width:800px;
    height:580px;
    border:1px solid lightgrey;
}
</style>