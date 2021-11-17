<template>
  <div id="Calender_Container">
    <v-sheet tile height="45" class="d-flex">
      <!-- 오늘 날짜로 가기 -->
      <v-btn
        outlined
        class="mr-4"
        color="grey darken-2"
        @click="setToday"
      >
        Today
      </v-btn>
      <!-- 뒤로 가기 -->
      <v-btn
        icon
        class="ma-2"
        style="position: relative; top: -15px"
        @click="$refs.calendar.prev()"
        large
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-toolbar-title v-if="$refs.calendar">
        {{ $refs.calendar.title }}
      </v-toolbar-title>
      <!-- 앞으로 가기 -->
      <v-btn
        icon
        class="ma-2"
        style="position: relative; top: -15px"
        @click="$refs.calendar.next()"
        large
      >
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
    </v-sheet>
    <!-- 달력 -->
    <v-sheet height="600">
      <v-calendar
        ref="calendar"
        v-model="focus"
        :events="events"
        :type="type"
        :event-overlap-threshold="30"
        :event-color="getEventColor"
        @change="getEvents"
        @click:event="showEvent"
        @click:more="dialog = true"
      ></v-calendar>
      <!-- @change="getEvents" -->
      <v-menu
        v-model="selectedOpen"
        :close-on-content-click="false"
        :activator="selectedElement"
        offset-x
      >
        <v-card
          color="rgb(255 253 250)"
          min-width="400px"
          flat
        >
          <div style="height:300px;" v-if="selectedEvent.noteMedia">
            <v-img style="height:100%;" :src="selectedEvent.noteMedia" alt="일기 사진"/>
          </div>
          <v-card-title>
            <span>{{selectedEvent.name}} </span>
            <div style="font-size:14px; margin-left:200px;">작성자:{{selectedEvent.writerNickName}}</div>
            <div style="font-size:11px;">({{selectedEvent.time}})</div>
          </v-card-title>
          <v-card-actions>
            <v-btn
              color="orange lighten-2"
              text
            >
              일기 내용 보기
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
              icon
              @click="show = !show"
            >
              <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
          </v-card-actions>
          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>
              <v-card-text id="select-box"> 
                <span>{{selectedEvent.content}}</span>
                <div v-for="(hashtag, i) in selectedEvent.hashTag" :key="i">
                  <span style="font-size:14px; color:#ffb319">#{{hashtag}}</span>
                </div>
              </v-card-text>
            </div>
          </v-expand-transition>
          <v-card-actions>
            <v-btn
              text
              @click="selectedOpen = false"
            >
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-menu>
    </v-sheet>
    <!-- 더보기 눌렀을때의 Dialog -->
      <v-dialog v-model="dialog" persistent max-width="280">
      <v-card 
        class="mx-auto"
        max-width="280"
        tile>
        <div>
          <v-icon
            @click="dialog = false"
            id="Dialog_Close"
            style="font-size: 1.8rem; float: right; margin-top:5px; margin-left:15px;"
            >close</v-icon>
        </div>
        <v-list flat>
          <!-- <v-subheader>--일기 목록--</v-subheader> -->
          <v-list-item-group
            v-model="selectedItem"
            color="primary"
            style="margin-top:10px;"
          >
            <v-list-item
              v-for="(item, i) in items"
              :key="i"
              @click="showMore(item.idx)"
            >
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>
              <v-list-item-content >
                <v-list-item-title v-text="item.text" ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import dayjs from "dayjs";

export default {
  data() {
    return {
      focus: '',
      type: 'month',
      month: dayjs().format("MM"),
      // value: "",
      events: [],
      colors: [
        "rgb(177, 177, 233)",
        "rgb(200, 178, 216)",
        "rgb(156, 218, 218)",
        "rgb(157, 230, 157)",
        "rgb(236, 205, 148)",
        "rgb(253, 187, 251)",
        "rgb(255, 149, 149)",
        "rgb(244, 247, 111)",
      ],
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      show: false,
      dialog: false,
      selectedItem: 1,
      items: [],
    };
  },
  methods: {
    // 달마다 일기가져오기
    getEvents() {
      this.$store.dispatch("calenderGet", this.month).then((res) => {
        const events = []
        console.log(res.data, 'aaaaaaaaaaa')
        for(let i = 0; i < res.data.length; i++) {
          const note = {}           // 더보기 눌렀을때 보여질 일기제목담음
          const event = {
            name: res.data[i].noteTitle,
            id: res.data[i].noteId,
            writerNickName: res.data[i].writerNickName,
            content: res.data[i].noteContent,
            hashTag: res.data[i].noteHashtagList,
            font: res.data[i].fontId,
            start: res.data[i].noteCreatedDate,
            end: res.data[i].noteCreatedDate,
            noteMedia: res.data[i].noteMediaList[0],
            color: this.colors[this.rnd(0, this.colors.length - 1)],
            timed: true,
            time: res.data[i].noteCreatedTime
          }
          note.text = res.data[i].noteTitle
          note.idx = res.data[i].noteId,
          this.items[i] = note
          events[i] = event
        }
        this.events = events;
      });
    },
    getEventColor(event) {
      return event.color;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    prev() {
      this.month = this.month - 1;
    },
    next() {
      this.month = this.month + 1;
    },
    // 달력에서 보이는 일기들 누를떄
    showEvent ({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event
        this.selectedElement = nativeEvent.target
        requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
      }

      if (this.selectedOpen) {
        this.selectedOpen = false
        requestAnimationFrame(() => requestAnimationFrame(() => open()))
      } else {
        open()
      }
      nativeEvent.stopPropagation()
    },
    // 더보기 리스트에서 일기볼때
    showMore (idx) {
      let nowSelect = []
      for (var i = 0; i < this.events.length; i++) {
        if (this.events[i].id == idx) {
          nowSelect = this.events[i]
          break
        }
      }
      const open = () => {
        this.selectedEvent = nowSelect
        this.selectedElement = 'div.pl-1'
        requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
      }
      if (this.selectedOpen) {
        this.selectedOpen = false
        requestAnimationFrame(() => requestAnimationFrame(() => open()))
      } else {
        open()
      }
    },
    // 오늘 날짜로 이동
    setToday () {
      this.focus = ''
    },
  },
  created() {
  },
};
</script>

<style scoped>
#Calender_Container {
  margin: 0 auto;
  width: 95%;
}
#select-box {
  font-size: 18px;
  /* width: 400px;
  height: 430px; */
  /* display:flex;
  justify-content: center;
  align-items: 
  center; */
}

</style>