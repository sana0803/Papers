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