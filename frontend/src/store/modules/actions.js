import $axios from 'axios'

const baseUrl = 'https://localhost/api'

export function signUp({state}, user){
    console.log('signUp action 호출', state, user)
    const url = baseUrl + '/user'
    return $axios.post(url, user)   
}

export function login({state}, user){
    console.log('login action 호출', state, user)
    const url = baseUrl + '/auth'
    return $axios.post(url, user)
}