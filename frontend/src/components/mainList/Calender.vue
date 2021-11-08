<template>
  <div id="Calender_Container">
    <v-sheet tile height="45" class="d-flex">
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
    </v-sheet>
    <!-- 달력 -->
    <v-sheet height="600">
      <v-calendar
        ref="calendar"
        v-model="value"
        :events="events"
        :event-overlap-mode="mode"
        :event-overlap-threshold="30"
        :event-color="getEventColor"
        @change="getEvents"
        @click:event="showEvent"
      ></v-calendar>
      <!-- @change="getEvents" -->
       <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
          >
            <v-toolbar
              :color="selectedEvent.color"
              dark
            >
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-card-text id="select-box">
              {{selectedEvent.content}} 
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                color="secondary"
                @click="selectedOpen = false"
              >
                닫기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
    </v-sheet>
  </div>
</template>

<script>
import dayjs from 'dayjs'

export default {
  data() {
    return {
      month: dayjs().format("MM"),
      value: "",
      events: [],
      colors: [
        "blue",
        "indigo",
        "deep-purple",
        "cyan",
        "green",
        "orange",
        "grey darken-1",
      ],
      names: [
        "Meeting",
        "Holiday",
        "PTO",
        "Travel",
        "Event",
        "Birthday",
        "Conference",
        "Party",
      ],
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
    }
  },
  methods: {
    getEvents() {
      this.$store.dispatch("calenderGet", this.month).then((res) => {
        const events = []

        for(let i = 0; i < res.data.length; i++) {
          const allDay = this.rnd(0, 3) === 0;

          const event = {
            name : res.data[i].noteTitle,
            id : res.data[i].noteId,
            content: res.data[i].noteContent,
            start: res.data[i].noteCreatedDate,
            end: res.data[i].noteCreatedDate,
            color: this.colors[this.rnd(0, this.colors.length - 1)],
            timed: allDay,
          }
          events[i] = event
        }
        this.events = events;
    });
    
      // const events = [];

      // const min = new Date(`${start.date}T00:00:00`);
      // const max = new Date(`${end.date}T23:59:59`);
      // const days = (max.getTime() - min.getTime()) / 86400000;
      // const eventCount = this.rnd(days, days + 20);

      // for (let i = 0; i < eventCount; i++) {
      //   const allDay = this.rnd(0, 3) === 0;
      //   const firstTimestamp = this.rnd(min.getTime(), max.getTime());
      //   const first = new Date(firstTimestamp - (firstTimestamp % 900000));
      //   const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
      //   const second = new Date(first.getTime() + secondTimestamp);

      //   events.push({
      //     name: this.names[this.rnd(0, this.names.length - 1)],
      //     start: first,
      //     end: second,
      //     color: this.colors[this.rnd(0, this.colors.length - 1)],
      //     timed: !allDay,
      //   });
      // }

      // this.events = events;
    },
    getEventColor(event) {
      return event.color;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    prev() {
      this.month = this.month - 1
    },
    next() {
      this.month = this.month + 1
    },
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
  },
  created() {
    // this.$store.dispatch("calenderGet", this.month).then((res) => {
    //   // this.calenderList = res.data.reverse()
    //   const events = []

    //   for(let i = 0; i < res.data.length; i++) {
    //     const allDay = this.rnd(0, 3) === 0;

    //     const event = {
    //       name : res.data[i].noteTitle,
    //       // id : res.data[i].noteId,
    //       color: this.colors[this.rnd(0, this.colors.length - 1)],
    //       timed: !allDay,
    //     }
    //     events[i] = event
    //   }
    //   this.events = events;
    // });
  },
};
</script>

<style scoped>
#Calender_Container {
  margin: 0 auto;
  width: 95%;
}
#select-box{
  width:400px;
  height:500px;
  background:yellow;
  display:flex;
  justify-content: center;
  align-items: center;
}
</style>