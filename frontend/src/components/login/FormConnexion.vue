<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire de connexion</b></h1>
	 	<div class="container">

			<form class="" @submit.prevent="submit()">
			  	<div class="control">
			  		<label class="label">E-mail :</label>
			    	<input class="input" type="mail" placeholder="Entrez votre E-mail" v-model="user.mail" required>
			  	</div><br>
			  	<div class="control">
			  		<label class="label">Password :</label>
			    	<b-input class="b-input" type="password" password-reveal v-model="user.password" required/>
			  	</div><br>

				 	<div class="control">
				    	<button class="button is-link">Connexion</button>
				 	</div>

					<router-link class="button is-primary" to="inscription" >S'inscrire</router-link>

			</form>
		</div>
	</div>
 	</layout-basic>
</template>

<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'
	import axios from 'axios'

export default {
	components: {
	  LayoutBasic
	},
	data: function () {
    return {
			user:{
				mail: "",
				password: ""
			}
		}
	},
	methods: {
		submit(){
			console.log(this.user.mail)
			console.log(this.user.password)
			this.$store.dispatch('auth/signin',this.user).then(response=>{
				this.$router.push({name:'admin'})
			})
			
		}
	}
}
</script>

<style scoped>
	.input,.b-input{
		width: 50%;
	}
	.container{
		width: calc( 100% - 2 * 5%);
		margin-left: 5%;
		margin-right: 5%;
		margin-top:60px;
		margin-bottom: 150px;
	}
	.controle{
		margin-top:25px;
		margin-bottom: 25px;
	}

	.button{
		background-color: #3273dc;
	}

	.titleFormBackend{
		background-color: #ccc;
		margin-top:20px;
		color:black;
		padding: 1%;
		font-size: 2em;
		width: auto;
		margin-left: 5px;
		margin-right: 5px;
	}
	.globalContainer{
		border:2px solid black;
		width: 80%;
		height: 700px;
		margin-top: 20px;
		margin-bottom: 10px;
		margin-left: 100px;
	}

</style>
