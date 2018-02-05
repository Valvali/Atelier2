import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import store from '@/store'


Vue.use(Router)

export const router = new Router({
  routes: [
  	{
    	path: '/Home',
    	name: 'home',
    	component: Home
    }

  ]
})
