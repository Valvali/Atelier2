<template>
	<layout-basic>
	<div class="globalContainer">
		<h1 class="titleFormBackend"><b>Formulaire d'ajout d'une partie</b></h1>
	 	<div class="container">

			<form class="" @submit.prevent="submit()" >
					<div class="coordonees">
						<div class="block">
							<p>Veuillez entrer les coordonnées manuellement ou cliquer sur la carte le point d'intéret</p><br>
							<div class="control">
					  		<label class="label">Latitude :</label>
					    	<input class="input" type="text" placeholder="Entrez la latitude" :pattern="finalRegex" @input="ifRegexLat()" @change="ifRegexLatEnd()" v-model="lat" required>
					  	</div><br>
					  	<div class="control">
					  		<label class="label">Longitude :</label>
					    	<input class="input" type="text" placeholder="Entrez la longitude" :pattern="finalRegex" @input="ifRegexLng()" @change="ifRegexLngEnd()" v-model="lng" required>
					  	</div><br>
							<div class="control">
					  		<label class="label">Ville :</label>
					    	<input class="input" type="text" placeholder="Entrez la ville" v-model="city" required>
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
							<vue-dropzone ref="myVueDropzone" id="dropzone" :options="dropzoneOptions"  v-model="img"/>
						</div>
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
				  		<label class="label">Description :</label>
				    	<b-input id="textarea" class="b-input" type="textarea" minlength="10" maxlength="100"
						placeholder="Entrez la descriptiond de la partie" v-model="description" required/>
				  	</div>
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

	import vue2Dropzone from 'vue2-dropzone'
	import 'vue2-dropzone/dist/vue2Dropzone.css'

export default {
	components: {
	  LayoutBasic,
		vueDropzone: vue2Dropzone
	},
	data: function () {
    return {
      zoom: 13,
      center: [48.6833, 6.19], //nancy
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      option: {},

			inputRegex: "^[0-9]+[\.]?([0-9]+$|$)",
			finalRegex: "^[0-9]+(\.[0-9]+$|$)",

			difficulty:"2",
			lat: "",
			lng: "",
			description: "",
			city: "",
			img:"",


			dropzoneOptions: {
          url: 'https://httpbin.org/post',
          thumbnailWidth: 500,
          maxFilesize: 0.5,
          headers: { "My-Awesome-Header": "header value"},
					maxFiles: "1",
					acceptedFiles: "image/png,image/gif,image/jpeg",
					addRemoveLinks: true,
      }
		}
	},
	methods: {
		async getPoint(e){
			this.lat = e.latlng.lat
			this.lng = e.latlng.lng
		},
		submit(){
			console.log(this.lat)
			console.log(this.lng)
			console.log(this.city)
			console.log(this.img)
			console.log(this.description)
		},
		ifRegexLat(){
			if(!this.lat.match(this.inputRegex)){
				this.lat = ""
			}
		},
		ifRegexLatEnd(){
			if(!this.lat.match(this.finalRegex)){
				console.log(false);
				this.lat = this.lat.replace("\.", "")
			}
		},
		ifRegexLng(){
			if(!this.lng.match(this.inputRegex)){
				this.lng = ""
			}
		},
		ifRegexLngEnd(){
			if(!this.lng.match(this.finalRegex)){
				console.log(false);
				this.lng = this.lng.replace("\.", "")
			}
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
	@media screen and (min-width: 900px) {
		/* block the size of the box to 65% without the media query*/
		.input,  .b-input, #dropzone{
			width: 65%;
		}
	}
	.input, .b-input, #dropzone{
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
		margin-left: 5%;
		margin-right:  5%;

	}

	@media screen and (max-width: 900px) {
	  .coordonees {
	    flex-direction: column;
	  }
	  .block{
	    margin: 0;
	    width : 100%;
	  }
		.globalContainer{
			border:2px solid black;
			width: 98%;
			margin-top: 20px;
			margin-bottom: 10px;
			margin-left: 1%;
			margin-right:  1%;

		}
		.input, .b-input, #dropzone{
			width: 100%;
		}
	}

</style>
