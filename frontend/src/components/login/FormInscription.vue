<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire d'inscription</b></h1>
	 	<div class="container">

			<form class="form" @submit.prevent="submit(name, email, password)">
			  	<div class="control">
			  		<label class="label">Nom :</label>
			    	<input class="input" type="text" placeholder="Entrez votre nom complet" v-model="user.nom" required>
			  	</div><br>
			  	<div class="control">
			  		<label class="label">E-mail :</label>
			    	<input class="input" type="email" placeholder="Entrez votre E-mail" v-model="user.mail" required>
					</div><br>
					<div class="control">
						<label class="label">Mot de passe :</label>
						<input class="input" type="password" password-reveal v-model="user.password" required>
					</div><br>
					<div class="control">
						<label class="label">Verification du mot de passe</label>
						<input class="input" type="password" password-reveal v-model="passwordVerif" required >
						<div class="messageError" v-if="matchPassword">
							<strong>Les mots de passes ne correspondent pas !</strong>
						</div>
					</div><br>


				 	<div class="control">
				    	<button class="button is-link">Enregistrer</button>
				 	</div>

					<router-link class="button is-primary" to="connexion" >Se connecter</router-link>
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
				nom: "",
				mail: "",
				password: "",
			},
			matchPassword: false,
			passwordVerif: ""
		}
	},
	methods: {
		submit(name, mail, pw){
			if(this.verifPassword()){
				console.log(this.user.nom)
				console.log(this.user.mail)
				console.log(this.user.password)
				this.$store.dispatch('auth/signup',this.user).then(response=>{
					this.$router.push({nom:'admin'})
				})
			}
		},
		verifPassword(){
			if(this.user.password == this.passwordVerif){
				this.matchPassword = false
				return true
			}else{
				this.matchPassword = true
				return false
			}
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
		width: calc( 100% - 2 * 10% );
		height: 700px;
		margin: 20px 10% 10px 10%;

	}
	.messageError strong{
		color: red;
		text-align: left;
	}
	@media screen and (max-width: 900px) {
		.globalContainer{
			border:2px solid black;
			width: calc( 100% - 2 * 2.5% );
			height: 700px;
			margin: 20px 2.5% 10px 2.5%;

		}
		.input,.b-input{
			width: 100%;
		}

	}

</style>
