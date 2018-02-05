<template>
	<layout-basic>
	 	<div class="container">
			<form class="" action="index.html" @submit.prevent="newGame()">
		    <div class="field">
			  	<div class="control">

			  		<label class="label">Ville :</label>
						<select v-model="city">
  						<option value="nancy">Nancy</option>
  						<option value="paris">Paris</option>
  						<option value="lyon">Lyon</option>
						</select>
			  	</div><br>

			  	<div class="control">
			  		<label class="label">Difficulté :</label>
						<select v-model="difficulty">
  						<option value="1">Facile</option>
  						<option value="2" selected>Moyen</option>
  						<option value="3">Difficile</option>
							<option value="4">Expert</option>
						</select>
			  	</div><br>

			  	<div class="control">
			  		<label class="label">Username :</label>
			    	<input class="input" type="text" placeholder="Saisissez votre username" v-model="pseudo">
			  	</div>
				</div>

				<div class="field is-grouped">
				 	<div class="control">
				    	<button class="button is-link">Envoyé</button>
				 	</div>
				</div>
			</form>
		</div>
 	</layout-basic>
</template>

<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'

export default {
	components: {
	  LayoutBasic
	},
	data: function () {
	  return {
			pseudo: "",
			difficulty: 2,
	    city: "nancy",
	  };
	},
  methods: {
		newGame() {
			console.log(this.pseudo);
			console.log(this.difficulty);
			console.log(this.city);
		}
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
