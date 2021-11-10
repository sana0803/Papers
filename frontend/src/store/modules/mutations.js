

export function setLoginUser(state, loginUser) {
  state.loginUser = loginUser
}

export function setCurrentDiary(state, currentDiary) {
  state.currentDiary = currentDiary
}

export function setAlarmEventSource(state, alarmEventSource) {
  state.alarmEventSource = alarmEventSource
}

// 오른쪽이 바꿀 값
export function setDiaryContent(state, diaryContent) {
  state.diaryContent = diaryContent
}

export function setNoteContent(state, note) {
  console.log('일기 낱장 받아오기 mutation 호출', state)
  state.noteContent = note
}

export function setNotificationState(state, notificationState) {
  state.notificationState = notificationState
  console.log(notificationState + '를 변경함')
}

export function setNotificationMessage(state, message) {
  state.notificationMessage = message
}

export function setNotificationUserImage(state, image) {
  state.notificationUserImage = image
}

export function setNotifications(state, notifications) {
  state.notifications = notifications
}

// export function setNoteContent(state, noteContent) {
//   state.noteContent = noteContent
// }

export function setIsUpdate(state, isUpdate) {
  state.isUpdate = isUpdate
}

export function initNoteContent(state) {
  state.noteContent = {
    noteId: '',
    diaryId: '',
    fontId: '',
    layoutId: 1,
    designId: 1,
    writerId: '',
    noteTitle: '',
    noteContent: '',
    noteS3MediaList: [],
    noteMediaList: [],
    noteHashtagList: [],
    stickerList: [],
    emotionList: []
  }
}