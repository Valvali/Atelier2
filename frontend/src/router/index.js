import Vue from 'vue'
import Router from 'vue-router'

import store from '@/store'

import geoloc from '@/components/content_test/geolocalisation'

Vue.use(Router)

export const router = new Router({
  routes: [
    {
      path: '/geolocation',
      name: 'geoloc',
      component: geoloc,
    },

  ]
})
