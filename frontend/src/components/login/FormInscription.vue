<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire d'inscription</b></h1>
	 	<div class="container">

			<form class="" @submit.prevent="submit()">
			  	<div class="control">
			  		<label class="label">Nom :</label>
			    	<input class="input" type="text" placeholder="Entrez votre nom complet" v-model="name">
			  	</div><br>
			  	<div class="control">
			  		<label class="label">E-mail :</label>
			    	<input class="input" type="email" placeholder="Entrez votre E-mail" v-model="email">
					</div><br>
					<div class="control">
						<label class="label">Mot de passe :</label>
						<input class="input" type="password" placeholder="Entrez votre mot de passe" password-reveal v-model="password" >
					</div><br>
					<div class="control">
						<label class="label">Verification du mot de passe</label>
						<input class="input" type="password" placeholder="Confirmez votre mot de passe" password-reveal v-model="passwordVerif" >
					</div><br>


				 	<div class="control">
				    	<button class="button is-link">Enregistrer</button>
				 	</div>
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
			name: "",
			email: "",
			password: "",
			passwordVerif: "",
		}
	},
	methods: {
		submit(){
			console.log(this.name)
			console.log(this.email)
			console.log(this.password)
		},
		verifPassword(){
			
		}
	}
}
</script>

<style scoped>
	.input,.b-input{
		width: 65%;
	}
	.container{
		margin-left: 10%;
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
