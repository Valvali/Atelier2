import api from '../../services/api'
import ls from '@/services/ls'

const initialState = {
	connected: false,
	user: {}
}

export default {
	namespaced: true,//permet d'y accéder de façon nommée
	state: {
		connected:false,
		user:{}
	},
	getters: {
		isConnected(state){
			return state.connected
		},
		getConnectedUser(state){
			return state.user
		}
	},
	mutations: {
		setConnectedUser(state,u){
			state.user=u
			state.connected=true
		},
		initState(state){
			Object.assign(state, initialState)
		}
	},
	actions: {
		login({commit},credentials){
			api.post('http://localhost:8080/user',credentials).then(response=>{
				ls.set('token',response.data.token)
				commit('setConnectedUser', response.data)
			}).catch(error => {
				console.log(error)
				})
		}
	}
}
