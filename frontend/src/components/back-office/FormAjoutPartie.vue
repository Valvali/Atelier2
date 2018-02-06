<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire d'ajout d'une partie</b></h1>
	 	<div class="container">
	 	
			<form class="" >	
			  	<div class="control">
			  		<label class="label">Latitude :</label>
			    	<input class="input" type="text" placeholder="Entrez l'altitude">
			  	</div><br>	
			  	<div class="control">
			  		<label class="label">Longitude :</label>
			    	<input class="input" type="text" placeholder="Entrez la longitude">
			  	</div><br>	
			  	<div class="control">
			  		<label class="label">Image :</label>
			    	<input type="file">
			  	</div><br>	
			  	<div class="control">
			  		<label class="label">Description :</label>
			    	<b-input id="textarea" class="b-input" type="textarea" minlength="10" maxlength="100"
					placeholder="Entrez la descriptiond de la partie"/>
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
