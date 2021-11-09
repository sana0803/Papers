export default function () {

    // 로그인 유저 정보
    const loginUser = {}

    // 현재 보고있는 일기장
    const currentDiary = {}
    
    const alarmEventSource = {} // 알람 전송을 위한 채널

    const diaryContent = {}

    const noteContent = {}
    
    const notificationState = false
  
    const notificationMessage = ''

    return {
      loginUser,
      currentDiary,
      alarmEventSource,
      diaryContent,
      noteContent,
      notificationState,
      notificationMessage,
    }
}