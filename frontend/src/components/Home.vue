<template>
	<layout-basic>
	<h1 class="homeTitle"><b>Welcome to our application GeoQuizz, please launch the game...</b></h1>
	 	<div class="container">

			<!--<form class="" action="index.html" @submit.prevent="newGame(city)">-->


			<form class="" action="index.html" @submit.prevent="newGame()">

			  	<div class="control">

						<b-field label="Ville :">
							<b-select placeholder="Selectionner une ville" v-model="serie">
								<option v-for="s in series">{{ s }}</option>
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
			  		<label class="label">Pseudo :</label>
			    	<input class="input" type="text" placeholder="Saisissez votre username" v-model="pseudo" required>
			  	</div><br>

				 	<div class="control">
				    	<button class="button is-link">Envoyer</button>
				 	</div>
			</form>
		</div>
 	</layout-basic>
</template>



<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'
	import ls  from '@/services/ls'
	import store from '@/store'



export default {
	components: {
	  LayoutBasic
	},
	data: function () {
	  return {
		pseudo: "",
		difficulty: "2",
	  serie: "",
		series: []
	  };
	},
  methods: {
		async newGame() {/*
			await api.get('partie/' + this.city).then(function (response) {
				console.log(response);
			})*/

			let playerInfo = {
				"pseudo":this.pseudo,
				"score": 0,
				"difficulty":this.difficulty,
				"city": this.serie,
			}
			ls.set(0, playerInfo)
			this.$router.push({'name': 'geoloc'})
		}
	},
	created(){
		ls.clear()
		api.get('/serie').then(response=>{
			this.series=response.data;
		}).catch((err) => {
			  console.log(err);
		})
		if (store.getters['auth/isConnected']) {
			this.pseudo = store.getters['auth/getConnectedUser']
		}

	}
}
</script>

<style scoped>
	.input{
		width: 80%;
	}
	.container{
		width: calc( 100% - 2 * 30%);
		margin-left: 30%;
		margin-right: 30%;

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


	h1.homeTitle{
		color:black;
		font-size: 2em;
		margin-top:50px;
	}
	@media screen and (max-width: 900px) {
		.container{
			width: calc( 100% - 2 * 25%);
			margin-left: 25%;
			margin-right: 25%;

			margin-top:60px;
			margin-bottom: 150px;
		}
		.container *{
			margin : auto 5px auto 5px;
		}
	}
	@media screen and (max-width: 500px) {
		.container{
			width: calc( 100% - 2 * 2.5%);
			margin-left: 2.5%;
			margin-right: 2.5%;
		}
	}

</style>
