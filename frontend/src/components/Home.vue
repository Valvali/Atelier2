<template>
	<layout-basic>
	<h1 class="homeTitle"><b>Welcome to our application GeoQuizz, please lunch the game...</b></h1>
	 	<div class="container">
			<form class="" action="index.html" @submit.prevent="newGame(city)">
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
			  	</div><br>

				 	<div class="control">
				    	<button class="button is-link">Envoyer</button>
				 	</div>
			</form>
			todo list :<br>
			- recuperer les infos de l'api avec le formulaire<br>
			- mettre le fichier json des points dans le store pour l'utiliser dans geolocation<br>
			- permettre de stocker le score dans le store puis pour l'afficher et/ou continuer<br>
			- post / get le liste des meilleur particiapnt<br>

		</div>
 	</layout-basic>
</template>

<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'
	import axios from 'axios'
	import ls  from '@/services/ls'

export default {
	components: {
	  LayoutBasic
	},
	data: function () {
	  return {
			pseudo: "",
			difficulty: 2,
	    	city: "nancy",
	    	serie:{}
	  };
	},
  methods: {
		async newGame(ville) {
			await api.get('partie/' + ville).then(function (response) {
				console.log(response);
			})
			console.log(this.pseudo);
			console.log(this.difficulty);
			console.log(this.city);

			let playerInfo = {
				"pseudo":this.pseudo,
				"score": 0,
				"difficulty":this.difficulty,
				"city": this.city,
			}
			ls.set (0, playerInfo)
			this.$router.push({'name': 'geoloc'})
		}
	},

	//wait for backend
	created(){
		axios.get('').then(response=>{
			this.serie=response.data;
			console.log(this.serie);
			console.log('okkkkk');
		}).catch((err) => {
			  console.log(err);
			})
	},
	created: function () {
		ls.clear()
		console.log(ls.get(0));
	}
}
</script>

<style scoped>
	.input{
		width: 40%;
	}
	.container{
		margin-left: 30%;
		margin-top:100px;
		margin-bottom: 200px;
	}
	.controle{
		margin-top:25px;
		margin-bottom: 25px;
	}
	.button{
		background-color: #3273dc;
	}

	h1.homeTitle{
		color:black;
		font-size: 2em;
		margin-top:50px;
	}
</style>
