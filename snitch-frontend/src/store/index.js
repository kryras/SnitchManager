import { createStore } from 'vuex'
import Vue from 'vue';
import Vuex from 'vuex';

import { auth } from './auth.module';

export default createStore({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth
  }
})

// export default new Vuex.Store({
//   modules: {
//     auth
//   }
// });