import Vue from 'vue'
import Vuex from 'vuex'
import state from './modules/state.js'
import * as actions from './modules/actions.js'
import * as mutations from './modules/mutations.js'
import * as getters from './modules/getters.js'
import Login from './modules/Login.js'

Vue.use(Vuex)

export default new Vuex.Store({
  // namespaced: true,
  state,
  mutations,
  actions,
  getters,
  modules: {
    Login:Login
  }
})
