import Vue from 'vue'
import Router from 'vue-router'

//templates

import store from '@/store'
import ls  from '@/services/ls'

import Home from '@/components/Home'
import geoloc from '@/components/content_test/geolocalisation'
import result from '@/components/content_test/result'
import backOffice from '@/components/back-office/FormAjoutPartie'
import inscription from '@/components/login/FormInscription'
import connexion from '@/components/login/FormConnexion'

Vue.use(Router)

export const router = new Router({
  routes: [
    {
    	path: '/',
    	name: 'home',
    	component: Home,
    },
    {
      path: '/geolocation',
      name: 'geoloc',
      component: geoloc,
      beforeEnter: (to, from, next) => {
        if (ls.isEmpty(0)) {
          next({path:"home"})
        } else {
          next(true)
        }
      }
    },
    {
    	path: '/result',
    	name: 'result',
    	component: result,
      beforeEnter: (to, from, next) => {
        if (ls.isEmpty(0)) {
          next({path:"home"})
        } else {
          next(true)
        }
      }
    },
    {
      path: '/admin',
      name: 'admin',
      component: backOffice,
      beforeEnter: (to, from, next) => {
        if (!store.getters['auth/isConnected']) {
          next({path:"connection"})
        } else {
          next(true)
        }
      }
    },
    {
      path: '/inscription',
      name: 'inscription',
      component: inscription,
      beforeEnter: (to, from, next) => {
        if (store.getters['auth/isConnected']) {
          next({path:"admin"})
        } else {
          next(true)
        }
      }
    },
    {
      path: '/connexion',
      name: 'connection',
      component: connexion,
      beforeEnter: (to, from, next) => {
        if (store.getters['auth/isConnected']) {
          next({path:"admin"})
        } else {
          next(true)
        }
      }
    },
    {
      path: '*',
      redirect: '/'
    }

  ]
})
