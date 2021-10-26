<template>
    <div id="MainList_Container">
        <div id="MainList_Head">
            <span id="Head_Main">분류</span>
            <div @click="select('list')" class="Head_Item">전체</div>
            <div @click="select('diary')" class="Head_Item">일기장</div>
            <div @click="select('calender')" class="Head_Item">날짜</div>
            <div @click="select('hashtag')" class="Head_Item">해시태그</div>
        </div>
        <div id="MainList_Content">
            <div v-if="MainListMode=='list'">
                <All />
            </div>
            <div v-if="MainListMode=='diary'">
                <Diary />
            </div>
            <div v-if="MainListMode=='calender'">
                <Calender />
            </div>
            <div v-if="MainListMode=='hashtag'">
                <Hashtag />
            </div>
        </div>
    </div>
</template>

<script>
import All from '../../components/mainList/All.vue'
import Diary from '../../components/mainList/Diary.vue'
import Calender from '../../components/mainList/Calender.vue'
import Hashtag from '../../components/mainList/Hashtag.vue'

export default {
    data() {
        return {
            MainListMode: 'list'
        }
    },
    components:{
        All,
        Diary,
        Calender,
        Hashtag
    },
    methods: {
        select(target) {
            this.MainListMode = target
        }
    }
}
</script>

<style scoped>
#MainList_Container{
    margin:0 auto;
    width:985px;
    height:92.5vh;
}
#MainList_Head{
    padding-top:48px;
    height:76px;
}
#Head_Main{
    color:#FFB319;
    font-size:20px;
    margin-left:18px;
}
.Head_Item{
    display:inline-block;
    font-size:18px;
    margin-left:36px;
    cursor: pointer;
    color:gray;
}
#MainList_Content{
    margin-top:24px;
    height:82vh;
    padding-top:10px;
    overflow:auto;
    -ms-overflow-style:none;
    scrollbar-width:none;
}
#MainList_Content::-webkit-scrollbar{
    display:none;
}
</style>