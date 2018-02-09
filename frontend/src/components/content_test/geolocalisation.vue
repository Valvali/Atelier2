<template>
  <layout-basic>
    <div class="content_test" v-if="!errorList">

        <v-map id="map" :zoom="zoom" :center="center" :options="option" v-on:l-click="getPoint($event)" >

          <v-tilelayer :url="url" ></v-tilelayer>
          <ul v-for="(item) in underNumber()" >
            <v-marker :lat-lng='[item.lat, item.lng]' :options="markerOption"></v-marker>
          </ul>
        </v-map>
        <div class="info">


          <div v-if="number != iterationMax" >
            <h1 class="note">N° : {{number + 1}} </h1>
            <h1 class="note">niveau : {{difficulty}} </h1>
            <h1 class="note">Temps : {{time}} </h1>
          </div>
          <h1 class="note">Score : {{score}} </h1>

          <img class="tips" :src="img" alt="photo">
          <div class="description">
            <h3><strong>Description :</strong></h3>
            <p>{{descr}}
            </p>
          </div>
        </div>
    </div>
    <div class="content_test" v-else>
      <div class="center">
        <h1 class="homeTitle"><b>La liste selectionnée ne possède pas suffisament de point pour jouer</b></h1>
        <button class="button is-primary" @click="home()">Retour vers l'accueil</button>
      </div>

    </div>
  </layout-basic>
</template>

<script>
import api from '@/services/api'
import LayoutBasic from '@/components/layout/BaseLayout'
import ls  from '@/services/ls'
import Vue from 'vue'

import config from '@/config'


import json from '../../../assets/donneestest.json'


import Vue2Leaflet from 'vue2-leaflet';

Vue.component('v-map', Vue2Leaflet.Map);
Vue.component('v-tilelayer', Vue2Leaflet.TileLayer);
Vue.component('v-marker', Vue2Leaflet.Marker);

export default {
  components: {
      LayoutBasic
  },
  name: 'App',
  data: function () {
    return {
      errorList: false,

      zoom: 13,
      center: [48.6833, 6.19], //nancy


      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      option: { zoomControl: false, dragging: false, doubleClickZoom:false, trackResize:false, minZoom:this.zoom, maxZoom:this.zoom, scrollWheelZoom:false},
      markerOption:{ draggable:false, opacity:0.7, interactive:false },

      iterationMax: 10,
      score:0,
      time: 0,
      difficulty: 1,
      number : 0,
      img: "",
      position: L.latLng(48.6833, 6.2),
      descr: "",

      RayonValid: 2000,
      interval: null,

      playerInfo: {},

      donnees: {
        token: "",
        points: []
      },
      //markerIcon: "../../assets/marker.png",
    };
  },
  methods: {
    home(){
      this.$router.push({'name': 'home'})
    },
    multiplyByTime(val){
      if(this.time<5){
        val = val*5
      }else if (this.time<10) {
        val = val*2.5
      }else if (this.time<20) {
        val = val*1.5
      }else if (this.time<30) {
        //res = res
      }else{
        val = 0
      }
      val = Math.round(val)
      return val
    },
    multiplyByDifficulty(val){
      switch (this.difficulty) {
        case "1" :
          val = val*0.5
          break;
        case "2" :
          val = val*1
          break;
        case "3" :
          val = val*2
          break;
        case "4" :
          val = val*4
          break;
        default:
          val = val*1
      }
      val = Math.round(val)
      return val
    },
    count() {
      this.interval = setInterval(()=> {
        this.time++
        if(this.time>30){
          clearInterval(this.interval);
          this.time = "fin du temps"
        }
      },1000)
    },
    underNumber(){
      let ret = []
      for(let i=0 ; i<this.number ; i++){
        ret.push(this.donnees.points[i])
      }
      if(this.number>= this.iterationMax ){
        this.position =  L.latLng(0 , 0);
        this.img = "http://ak8.picdn.net/shutterstock/videos/26123588/thumb/9.jpg"
        this.descr = "le jeu est terminée, veuillez cliquez sur la carte pour voir le tableau des scores"
        this.difficulty = 1
      }else{
        this.position = L.latLng(this.donnees.points[this.number].lat , this.donnees.points[this.number].lng)
        this.img = this.donnees.points[this.number].img
        if (this.img.startsWith('/')) {
          this.img = config.url + this.img
        }
        this.descr = this.donnees.points[this.number].description
        this.difficulty = this.donnees.points[this.number].difficulte

      }
      return ret
    },







    async getPoint(e){
      //need stop interval
      clearInterval(this.interval);
      let click = L.latLng(e.latlng.lat,e.latlng.lng);
      let res = (this.RayonValid - this.position.distanceTo(click)) / 2
      res = Math.round(res)
      if(res<0){res = 0}

      res += this.multiplyByTime(res)
      res += this.multiplyByDifficulty(res)
      this.score += res


      let playerInfo = {
          "pseudo":ls.get(0).pseudo,
          "score": this.score,
          "difficulty":ls.get(0).difficulty,
          "city": ls.get(0).city,
      }

      if(this.number>= this.iterationMax){
        //end of the game
        this.$router.push({'name': 'result'})
      }else{
        //redirection vers la page suivante
        this.time = 0
        this.number++
        this.$router.push({'name': 'geoloc'})

        await ls.set (0, playerInfo) //push
        if(this.number != this.iterationMax ){

          this.count()
        }
      }

    },
  },
  created: function () {
    this.count();

    this.playerInfo = {
      "pseudo":ls.get(0).pseudo,
      "score": this.score,
      "difficulty":ls.get(0).difficulty,
      "city": ls.get(0).city,
    }

    api.post('/partie/' + this.playerInfo.city + '/' + this.playerInfo.difficulty).then(response=>{
			this.donnees=response.data;
		}).catch((err) => {
			  console.log(err);
        this.errorList = true;
		})
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "~leaflet/dist/leaflet.css";
.content_test{
  min-height: 500px;
  display: flex;
  margin:10px;
  text-align: left;
}
#map {
  display: inline-block;
  cursor: pointer;
  height: 700px;
  width : 75%;
}
.info{
  margin: 10px;
  display: inline-block;
  width : 23%;
  vertical-align: top;
}
.note{
  margin-left: 15px;
  font-size: 20px;
  font-weight: bold;
}
.tips{
  margin:0px;
  display: block;
  width: 100%;
}
.center{
  width: 100%;
  margin: 100px 0 100px 0;
  text-align: center;
}

@media screen and (max-width: 900px) {
  .content_test {
    flex-direction: column-reverse;
  }
  #map, .info{
    margin: 0;
    width : 100%;
  }
}
</style>
