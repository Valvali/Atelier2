<template>
	<layout-basic>
	<h1 class="homeTitle"><b>Welcome to our application GeoQuiz</b></h1>
	 	<div class="container" v-if="info">
			<button class="button is-link" @click="reverseInfo()"> Information</button>
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
				    	<button class="button is-link">Commencer le test</button>
				 	</div>
			</form>
		</div>
		<div class="container" v-else>
			<button class="button is-link" @click="reverseInfo()"> Commencer la partie</button>
			<div class="textInfo">
				<p>
					Géoquiz est une application de jeu de géolocalisation. Après avoir rempli le formulaire
					pour connaître la ville, la difficulté et votre pseudo, le test commence.<br>
					Le principe est simple, l'application vous fournit une image et l'objectif
					est de retrouver la localisation du lieu en photo, pour cela il faut cliquer sur la carte
					<strong>MAIS ATTENTION</strong> il n'y a qu'une seule tentative.

					<h2>Le calcul des points :</h2>

					<div class="listPoint">
						<h3>Distance :</h3>
							<table>
								<tr>
							    <th>< 2 000 m </th>
							    <th>> 2 000 m</th>
							  </tr>
							  <tr>
							    <td>distance du point / 2</td>
							    <td>le score est de 0</td>
							  </tr>
							</table>

						<h3>Temps :</h3>
						<table>
							<tr>
								<th>< 5 s </th>
								<th>< 10 s </th>
								<th>< 20 s </th>
								<th>< 30 s </th>
								<th>> 30 s</th>
							</tr>
							<tr>
								<td>score x 5</td>
								<td>score x 2,5</td>
								<td>score x 1,5</td>
								<td>score x 1</td>
								<td>le score est de 0</td>

							</tr>
						</table>

						<h3>Difficulté :</h3>
							<table>
								<tr>
									<th> Facile </th>
									<th> Moyen </th>
									<th> Difficile </th>
									<th> Expert </th>
								</tr>
								<tr>
									<td>score x 0,5</td>
									<td>score x 1</td>
									<td>score x 2</td>
									<td>score x 4</td>

								</tr>
							</table>
					</div>



				</p>
			</div>
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
			info:true,
			pseudo: "",
			difficulty: "2",
			serie : "",
	    series:[],
	  };
	},
  methods: {
		reverseInfo(){
			this.info = !this.info
		},
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


	.textInfo{
		text-align: left;
		margin-top:25px;
		margin-bottom: 25px;
	}
	.textInfo p {
		Line-Height: 1.8;
	}
	.textInfo h2 {
		margin-top: 25px;
		font-weight: bold;
		font-size: 1.5em
	}
	.listPoint{
		width: calc(100% - 2 * 5%);
		margin: 0 5% 0 5%;
	}
	.listPoint h3{
		font-weight: bold;
		font-size: 1.2em;
		margin-bottom: 10px;
		margin-top: 20px;

	}
	.listPoint table  {
		width: 100%;
	}
	.listPoint table th {
		text-align: center;
    background-color: #714dd2;
    color: white;
	}
	.listPoint table td{
		text-align: center;
	}


	@media screen and (max-width: 900px) {
		.container{
			width: calc( 100% - 2 * 15%);
			margin-left: 15%;
			margin-right: 15%;

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
		.listPoint{
			width: 100%;
			margin: 0 ;
		}
	}

</style>
