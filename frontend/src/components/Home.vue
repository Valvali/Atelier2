<template>
	<layout-basic>
	 	<div class="container">

		    <div class="field">  		
			  	<div class="control">
			  		<label class="label">Ville :</label>
			    	<input class="input" type="text" placeholder="Entrez votre ville">
			  	</div><br>
		  		
			  	<div class="control">
			  		<label class="label">Difficulté :</label>
			    	<input class="input" type="number" placeholder="Entrez le niveau de difficulté">
			  	</div><br>
		  		
			  	<div class="control">
			  		<label class="label">Username :</label>
			    	<input class="input" type="text" placeholder="Saisissez votre username">
			  	</div>
			</div>

			<div class="field is-grouped">
			 	<div class="control">
			    	<button class="button is-link">Envoyé</button>
			 	</div>
			</div>
		</div>
 	</layout-basic>
</template>

<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'

	export default {
		components: {
	      LayoutBasic
	    }
	}
</script>

<style scoped>
	.input{
		width: 40%;
	}
	.container{
		margin-left: 30%;
		margin-top:5%;
		margin-bottom: 5%;
	}

	.button{
		background-color: #3273dc;
	}

</style>