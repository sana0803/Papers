<template>
    <div id="WriteSide_Container">
        <div id="WriteSide_Top">
            <div class="Line"></div>
        </div>
        <div id="WriteSide_Bot">
            <div class="Line"></div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'FrontendWriteside',

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style lang="scss" scoped>
#WriteSide_Container{
    width:20.5%;
    height:100%;
    border-right:1px solid lightgray;
    float:left;
}
#WriteSide_Top{
    height:340px;
}
#WriteSide_Bot{
    height:628px;
}
.Line{
    height:5px;
    background: #FFB319;
}
</style>