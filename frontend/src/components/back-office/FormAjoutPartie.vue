<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire d'ajout d'une partie</b></h1>
	 	<div class="container">

			<form class="" >
					<div class="coordonees">
						<div class="block">
							<p>Veuillez entrer les coordonnées manuellement ou cliquer sur la carte le point d'intéret</p><br>
							<div class="control">
					  		<label class="label">Latitude :</label>
					    	<input class="input" type="text" placeholder="Entrez l'altitude" pattern="^[0-9]+(\.[0-9]+$|$)" v-model="lat">
					  	</div><br>
					  	<div class="control">
					  		<label class="label">Longitude :</label>
					    	<input class="input" type="text" placeholder="Entrez la longitude" pattern="^[0-9]+(\.[0-9]+$|$)" v-model="lng">
					  	</div><br>
							<div class="control">
					  		<label class="label">Ville :</label>
					    	<input class="input" type="text" placeholder="Entrez la ville" v-model="city">
					  	</div><br>
						</div>
						<div class="block">
							<v-map id="map" :zoom="zoom" :center="center" :options="option" v-on:l-click="getPoint($event)" >
			          <v-tilelayer :url="url" ></v-tilelayer>
								<v-marker :lat-lng='[this.lat, this.lng]' ></v-marker>
			        </v-map>
						</div>
					</div>

			  	<div class="block">
						<div class="control">
				  		<label class="label">Image :</label>
				    	<input type="file">
				  	</div><br>
				  	<div class="control">
				  		<label class="label">Description :</label>
				    	<b-input id="textarea" class="b-input" type="textarea" minlength="10" maxlength="100"
						placeholder="Entrez la descriptiond de la partie" v-model="description"/>
				  	</div><br>
			  	</div>


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
      zoom: 13,
      center: [48.6833, 6.19], //nancy
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      option: {},

			lat: "",
			lng: "",
			description: "",
			city: "",
		}
	},
	methods: {
		async getPoint(e){
			this.lat = e.latlng.lat
			this.lng = e.latlng.lng
		}
	},




}
</script>

<style scoped>
@import "~leaflet/dist/leaflet.css";
	#map {
	  display: inline-block;
	  cursor: pointer;
	  height: 400px;
	  width : 100%;
	}
	.coordonees{
		display: flex;
		flex-direction: row;
	}
	.coordonees .block{
		width: 100%;
		margin : 0;
	}
	.block{
		text-align: left;
	}
	.input,.b-input{
		width: 65%;
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
		width: 90%;
		margin-top: 20px;
		margin-bottom: 10px;
		margin-left: 50px;
		margin-right:  50px;

	}

</style>
