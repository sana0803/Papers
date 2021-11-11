import $axios from 'axios'

const baseUrl = 'https://localhost/api'

export function signUp({ state }, user) {
    console.log('signUp action 호출', state, user)
    const url = baseUrl + '/user'
    return $axios.post(url, user)
}

export function login({ state }, user) {
    console.log('login action 호출', state, user)
    const url = baseUrl + '/auth'
    return $axios.post(url, user)
}

export function write({ state }, note) {
    console.log('write action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/note'
    return $axios.post(url, note, {
        headers: {
            Authorization: `Bearer ${userToken}`,
            // 'content-type' : 'multipart/form-data'
        }
    })
}

export function diaryCreate({ state }, diary) {
    console.log('diaryCreate action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/diary'
    return $axios.post(url, diary, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function diaryGet({ state }) {
    console.log('diaryGet action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/diary'
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function memberSearch({ state }, search) {
    console.log('memberSearch action 호출', state)
    const  url = baseUrl + '/user/search?searchUserId=' + search
    const userToken = state.loginUser.userToken
    return $axios.get(url,  { headers: { Authorization: `Bearer ${userToken}` } })
}

export function shareDiary({ state }, share) {
  console.log('shareDiary action 호출', state)
  const url = baseUrl + '/diary/invite'
  const userToken = state.loginUser.userToken
  return $axios.post(url, share, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function noteGet({ state }) {
    console.log('noteGet action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/note/note-list'
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function getDiaryContent({ state }, id) {
    console.log('getDiaryContent action 호출', state)
    const url = baseUrl + '/diary/' + id
    const userToken = state.loginUser.userToken
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function modifyNote({ state }, note) {
  console.log('일기 수정 action호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/note/' + note.noteId
	return $axios.put(url, note.formData,  { headers: { Authorization: `Bearer ${userToken}` } })
}
export function deleteNote({ state }, id) {
	console.log('deleteNote action호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/note/' + id
	return $axios.delete(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function modifyProfile({ state }, profile) {
	console.log('modifyProfile action호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/user' 
	return $axios.put(url, profile, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function calenderGet({ state }, month) {
    console.log('calenderGet action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/note/' + month
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function searchHashtag({ state }, search) {
    console.log('searchHashtag action 호출', state)
    const url = baseUrl + '/note/hashtag?hashtag=' + search
    const userToken = state.loginUser.userToken
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function getHashtag({ state }) {
    console.log('getHashtag action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/note/hashtag-list'
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function getCover({state }) {
    console.log('getCover action 호출', state)
    const userToken = state.loginUser.userToken
    const url = baseUrl + '/user/covers'
    return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function getUserFonts({ state }) {
	console.log('userFonts action 호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/user/fonts'
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function getUserStickers({ state }) {
	console.log('userStickers action 호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/user/stickers'
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function getUserCovers({ state }) {
	console.log('userCovers action 호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/user/covers'
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function getNotifications({ state }) {
  console.log('notifications load action 호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/notification'
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function readNotification({ state }, notificationId) {
  console.log('readNotification action 호출', state)
  console.log(notificationId)
  const userToken = state.loginUser.userToken
  const url = baseUrl + '/notification/' + notificationId
  return $axios.put(url, { headers: { Authorization: `Bearer ${userToken}` } })
}

export function getAllFonts({ state }) {
	console.log('font 전체 조회 호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/store/font'
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function buyFont({ state }, fontId) {
  console.log('font 구매 호출', state)
  const userToken = state.loginUser.userToken
  const url = baseUrl + '/store/font?fontId=' + fontId
  return $axios.put(url, '', { headers: { Authorization: `Bearer ${userToken}` } })
}

export function getStoreStickerList({ state }) {
	console.log('상점 스티커 목록 호출', state)
	const userToken = state.loginUser.userToken
	const url = baseUrl + '/store/sticker'
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function purchaseStickerPackage({ state }, id) {
	console.log('스티커 구매 호출', state)
	const userToken = state.loginUser.userToken
  const url = baseUrl + '/store/sticker?stickerPackageId=' + id;
	return $axios.put(url, '', { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function memberRemove({state}, remove) {
  console.log('memberRemove action 호출', state)
  const userToken = state.loginUser.userToken
	const url = baseUrl + '/diary/invite/?diaryId=' + remove.diaryId + '&userId=' + remove.userId
	return $axios.delete(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function getDiaryCoverList({state}) {
  console.log('상점 다이어리 커버 목록 호출', state)
  const userToken = state.loginUser.userToken
  const url = baseUrl + '/store/diary-cover';
	return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function purchaseDiaryCover({state}, id) {
  console.log('다이어리 커버 구매 호출', state)
  const userToken = state.loginUser.userToken
  const url = baseUrl + '/store/diary-cover/?diaryCoverId=' + id;
	return $axios.put(url, '', { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function emotionConfirm({state}, emotionReq) {
  console.log('감정 표현 호출', state)
  const userToken = state.loginUser.userToken
  const url = baseUrl + '/note/emotion'
	return $axios.post(url, emotionReq, { headers: { Authorization: `Bearer ${userToken}` } } )
}

export function emotionCancel({state}, emotionReq) {
  console.log('감정 표현 취소 호출', state)
  const userToken = state.loginUser.userToken
  const url = baseUrl + '/note/emotion?emotionInfoId=' + emotionReq.emotionInfoId + '&noteId=' + emotionReq.noteId
	return $axios.delete(url, { headers: { Authorization: `Bearer ${userToken}` } } )
}