<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire d'inscription</b></h1>
	 	<div class="container">
	 	
			<form class="" >	
			  	<div class="control">
			  		<label class="label">Nom :</label>
			    	<input class="input" type="text" placeholder="Entrez votre nom">
			  	</div><br>	
			  	<div class="control">
			  		<label class="label">Prénom :</label>
			    	<input class="input" type="text" placeholder="Entrez votre prénom">
			  	</div><br>	
			  	<div class="control">
			  		<label class="label">E-mail :</label>
			    	<input class="input" type="email" placeholder="Entrez votre E-mail">
			  	</div><br>	
			  	<div class="control">
			  		<label class="label">Password :</label>
			    	<b-input class="b-input" type="password" password-reveal placeholder="Entrez votre mot de passe"></b-input>
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
