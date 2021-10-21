import Vue from 'vue'
import Vuex from 'vuex'
import Login from './modules/Login';
import SignUp from './modules/SignUp';

Vue.use(Vuex)

export default new Vuex.Store({
  // state: {
  // },
  // mutations: {
  // },
  // actions: {
  // },
  // getters: {
  // },
  modules: {
    Login,
    SignUp
  }
})
