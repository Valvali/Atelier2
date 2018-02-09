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
						</div>
						<div class="block">
							<v-map id="map" :zoom="zoom" :center="center" :options="option" v-on:l-click="getPoint($event)" >
			          <v-tilelayer :url="url" ></v-tilelayer>
								<v-marker :lat-lng='[this.lat, this.lng]' ></v-marker>
			        </v-map>
						</div>
					</div>


						<div class="block" v-if="!newCity">
							<button class="button is-link" @click.prevent="reverseFormCity()"> Nouvelle ville</button>
							<label class="label">Ville :</label>
							<b-select placeholder="Selectionner une ville" v-model="city">
								<option v-for="s in series">{{ s }}</option>
							</b-select>
						</div>
						<div class="coordonees" v-else>
							<div class="block">
								<button class="button is-link" @click.prevent="reverseFormCity()"> Selecteur de ville</button>
								<label class="label">Nom de la nouvelle ville</label>
								<input class="input" type="text" placeholder="Entrez la nouvelle ville" v-model="nameNewCity" required>
								<div class="control"><br>
						  		<label class="label">Latitude :</label>
						    	<input class="input" type="text" placeholder="Entrez la latitude" :pattern="finalRegex" @input="ifRegexLat2()" @change="ifRegexLatEnd2()" v-model="newCityLat" required>
						  	</div><br>
						  	<div class="control">
						  		<label class="label">Longitude :</label>
						    	<input class="input" type="text" placeholder="Entrez la longitude" :pattern="finalRegex" @input="ifRegexLng2()" @change="ifRegexLngEnd2()" v-model="newCityLng" required>
						  	</div><br>
							</div>
							<div class="block">
								<v-map id="map" :zoom="zoom2" :center="center2" :options="option2" v-on:l-click="getPoint2($event)" >
				          <v-tilelayer :url="url" ></v-tilelayer>
									<v-marker :lat-lng='[this.newCityLat, this.newCityLng]' ></v-marker>
				        </v-map>
							</div>
						</div>
					<div class="block">

						<div class="control">
				  		<label class="label">Image :</label>
							<vue-dropzone ref="myVueDropzone" id="dropzone" :options="dropzoneOptions" v-on:vdropzone-success="success" v-model="img"/> <!-- -->
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
				    	<button type="submit" class="button is-link">Enregistrer</button>
				 	</div>
			</form>
		</div>
	</div>
 	</layout-basic>
</template>

<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'
	import ls  from '@/services/ls'

	import vue2Dropzone from 'vue2-dropzone'
	import 'vue2-dropzone/dist/vue2Dropzone.css'

export default {
	components: {
	  LayoutBasic,
		vueDropzone: vue2Dropzone
	},
	data: function () {
    return {
			url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      zoom: 13,
      center: [48.6833, 6.19], //nancy
      option: {},

			zoom2:5,
			center2:[47.9197, 2.4745],
			option2: {},

			inputRegex: "^[0-9]+[\.]?([0-9]+$|$)",
			finalRegex: "^[0-9]+(\.[0-9]+$|$)",

			newCity: false,
			nameNewCity:"",
			newCityLat: "",
			newCityLng: "",

			difficulty:"2",
			lat: "",
			lng: "",

			description: "",
			city: "",
			img:"",
			series:[],


			dropzoneOptions: {
          url: 'http://localhost:8080/api/file', //TODO url
          thumbnailWidth: 500,
          maxFilesize: 0.5,
          headers: { "autorization": ls.get('token')},
					maxFiles: "1",
					acceptedFiles: "image/png,image/gif,image/jpeg",
					addRemoveLinks: true,
      }
		}
	},
	methods: {
		success(file) {//https://github.com/rowanwins/vue-dropzone/issues/12
			 var response = JSON.parse(file.xhr.response)
			 console.log(response.files.file)
		 },

		reverseFormCity(){
			this.newCity = !this.newCity
		},
		getPoint(e){
			this.lat = e.latlng.lat
			this.lng = e.latlng.lng
		},
		getPoint2(e){
			this.newCityLat = e.latlng.lat
			this.newCityLng = e.latlng.lng
		},
		submit(){

			console.log(this.lat)
			console.log(this.lng)
			console.log(this.img)
			console.log(this.description)
			console.log(this.difficulty)

			let newPoints = [{
				lat: this.lat,
				lng: this.lng,
				img: 'https://www.petitfute.com/medias/professionnel/30049/premium/600_450/223989-nancy-place-stanislas.jpg', // TODO get response dropzone
				description: this.description,
				difficulte: this.difficulty
			}]

			if (this.newCity) {
				console.log("new city")
				console.log(this.nameNewCity)
				console.log(this.newCityLat)
				console.log(this.newCityLng)
				let ville = {
					lieu: this.nameNewCity,
					lat: this.newCityLat,
					lng: this.newCityLng,
					zoom: this.zoom2,
					points: newPoints
				}
				api.post('serie', ville)
			}else {
				console.log("old city")
				console.log(this.city)
				newPoints[0].serie = { lieu: this.city }
				api.post('point', newPoints[0])
			}

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
		},
		ifRegexLat2(){
			if(!this.newCityLat.match(this.inputRegex)){
				this.newCityLat = ""
			}
		},
		ifRegexLatEnd2(){
			if(!this.newCityLat.match(this.finalRegex)){
				console.log(false);
				this.newCityLat = this.newCityLat.replace("\.", "")
			}
		},
		ifRegexLng2(){
			if(!this.newCityLng.match(this.inputRegex)){
				this.newCityLng = ""
			}
		},
		ifRegexLngEnd2(){
			if(!this.newCityLng.match(this.finalRegex)){
				console.log(false);
				this.newCityLng = this.newCityLng.replace("\.", "")
			}
		}
	},
	created(){
		console.log(api);
		api.get('/serie').then(response=>{
			this.series=response.data
		}).catch((err) => {
			  console.log(err)
		})
		console.log(config.params.token)
	}

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
