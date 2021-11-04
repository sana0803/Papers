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
    const url = baseUrl + '/note'
    return $axios.post(url, note)
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