export default function () {

    // 로그인 유저 정보
    const loginUser = {}

    // 현재 보고있는 일기장
    const currentDiary = {}
    
    const alarmEventSource = {} // 알람 전송을 위한 채널

    const diaryContent = {}

  const noteContent = {
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
  
    let isUpdate = false
    
    const notificationState = false
  
    const notificationMessage = ''
    
    const notificationUserImage = ''
    
    const notifications = []
    
    const myFontList = {}

    return {
      loginUser,
      currentDiary,
      alarmEventSource,
      diaryContent,
      noteContent,
      isUpdate,
      notificationState,
      notificationMessage,
      notificationUserImage,
      notifications,
      myFontList
    }
}