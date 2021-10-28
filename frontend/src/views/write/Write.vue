<template>
    <div id="Write_Container">
        <write-side />
        <div id="Write_Main">
            <write-content />
        </div>
    </div>
</template>

<script>
import WriteSide from '../../components/write/WriteSide.vue'
import WriteContent from '../../components/write/WriteContent.vue'

export default {
    components:{
        WriteSide,
        WriteContent
    }
}
</script>

<style lang="scss" scoped>
#Write_Container{
    height:100vh;
}
#Write_Main{
    width:79.5%;
    float:right;
    height:100%;
    display:flex;
    justify-content: center;
    align-items: center;
}
</style>