import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import store from '@/store'
import geoloc from '@/components/content_test/geolocalisation'
import result from '@/components/content_test/result'
import backOffice from '@/components/back-office/FormAjoutPartie'

Vue.use(Router)

export const router = new Router({
  routes: [
    {
      path: '/geolocation',
      name: 'geoloc',
      component: geoloc,
    },

  	{
    	path: '/',
    	name: 'home',
    	component: Home
    },
    {
    	path: '/result',
    	name: 'result',
    	component: result
    },
    {
      path: '*',
      redirect: '/'
    },
    {
      path: '/admin',
      name: 'admin',
      component: backOffice
    }


  ]
})
