<template>
    <div id="Alert_Container">
        <div class="Alert_List">
            <div class="Alert_Day">오늘</div>
            <div class="Alert_Item">
                <div class="Item_Profile"></div>
                <div class="Item_Content"></div>
                <div class="Item_Time"></div>
            </div>
        </div>
    </div>
</template>

<script>
export default {

};
</script>

<style scoped>
#Alert_Container{
    padding-top:44px;
    height:92.5vh;
    overflow:auto;
    -ms-overflow-style:none;
    scrollbar-width:none;
}
#Alert_Container::-webkit-scrollbar{
    display:none;
}
.Alert_List{
    width:620px;
    margin:0 auto;
}
.Alert_Day{
    font-size:16px;
    font-weight:bold;
}
.Alert_Item{
    width:600px;
    height:75px;
    border:1px solid lightgray;
    border-radius: 15px;
    box-shadow: 1px 1px 3px grey;
    margin:0 auto;
    margin-top:15px;
    line-height:75px;
}
.Item_Profile{
    /* height:45px; */
    /* width:45px; */
}
.Item_Content{
    /* border:1px solid blue; */
}
.Item_Time{
    /* border:1px solid blue; */
}
</style>