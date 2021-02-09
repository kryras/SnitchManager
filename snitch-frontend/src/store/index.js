import { createStore, createLogger } from 'vuex'

import { auth } from './auth.module';

const store = createStore({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth
  },
  plugins: process.env.NODE_ENV !== 'production'
    ? [createLogger()]
    : [],
});

export default store;