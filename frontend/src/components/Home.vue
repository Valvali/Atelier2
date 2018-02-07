<template>
	<layout-basic>
	<h1 class="homeTitle"><b>Welcome to our application GeoQuizz, please launch the game...</b></h1>
	 	<div class="container">

			<!--<form class="" action="index.html" @submit.prevent="newGame(city)">-->
	 	

			<form class="" action="index.html" @submit.prevent="newGame()">

			  	<div class="control">

						<b-field label="Ville :">

							<b-select placeholder="Selectionner une ville" v-model="city" >
								<option value="nancy">Nancy</option>
								<option value="paris">Paris</option>
								<option value="lyon">Lyon</option>
							</b-select>
						</b-field>

			  	</div><br>

			  	<div class="control">
						<label class="label">Difficulté :</label>
						<b-field>
            <b-radio-button v-model="difficulty"
                native-value="1"
                type="is-primary">
                Façile
            </b-radio-button>

            <b-radio-button v-model="difficulty"
                native-value="2"
                type="is-primary"
								checked="checked">
                Normal
            </b-radio-button>

            <b-radio-button v-model="difficulty"
                native-value="3"
								type="is-primary">
                Difficile
            </b-radio-button>

            <b-radio-button v-model="difficulty"
                native-value="4"
								type="is-primary">
                Expert
            </b-radio-button>
        </b-field>


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
			difficulty: "2",
	    city: "nancy",
	    serie:{}
	  };
	},
  methods: {
		async newGame() {/*
			await api.get('partie/' + this.city).then(function (response) {
				console.log(response);
			})*/
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
	created(){
		ls.clear()
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
