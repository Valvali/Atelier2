<template>
  <layout-basic>
    <div class="content_test">

        <v-map id="map" :zoom="zoom" :center="center" :options="option" v-on:l-click="getPoint($event)" >

          <v-tilelayer :url="url" ></v-tilelayer>
          <ul v-for="(item) in underNumber()" >
            <v-marker :lat-lng='[item.lat, item.lng]' ></v-marker>
          </ul>
        </v-map>
        <div class="info">
          <h1 class="note">N° : {{number}} </h1>
          <h1 class="note">Score : {{score}} </h1>
          <h1 class="note">Temps : {{time}} </h1>

          <img class="tips" :src="img" alt="photo">
          <div class="description">
            <h3>description</h3>
            <p>lorem ipsum
            </p>
          </div>
        </div>
    </div>
  </layout-basic>
</template>

<script>
import api from '@/services/api'
import LayoutBasic from '@/components/layout/BaseLayout'
import ls  from '@/services/ls'
import Vue from 'vue'
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
      zoom: 13,

      center: [48.6833, 6.19], //nancy
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      option: { zoomControl: false, dragging: false, doubleClickZoom:false, trackResize:false, minZoom:this.zoom, maxZoom:this.zoom},

      score:0,
      time: 0,
      number : 0,
      img: "",
      position: L.latLng(48.6833, 6.2),

      RayonValid: 2000,
      interval: null,

      donnees : json, // à ne pas faire comme ça !!!!!
      //markerIcon: "../../assets/marker.png",
    };
	},
  methods: {
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
        ret.push(this.donnees[i])
      }
      if(this.number>= 10 ){
        this.position =  L.latLng(0 , 0);
        this.img = "http://ak8.picdn.net/shutterstock/videos/26123588/thumb/9.jpg"
      }else{
        this.position = L.latLng(this.donnees[this.number].lat , this.donnees[this.number].lng);
        this.img = this.donnees[this.number].img
      }
      return ret
    },
    async getPoint(e){
      //need stop interval
      clearInterval(this.interval);
      let click = L.latLng(e.latlng.lat,e.latlng.lng);
      let res = (this.RayonValid - this.position.distanceTo(click)) / 2
      if(res<0){res = 0}
      //console.log("distance = "+res);
      //console.log("temps = "+this.time);
      if(this.time<5){
        res = res*5
      }else if (this.time<10) {
        res = res*2.5
      }else if (this.time<20) {
        res = res*1.5
      }else if (this.time<30) {
        //res = res
      }else{
        res = 0
      }
      res = Math.round( res)
      this.score += res
      //console.log("score = "+res);

      let playerInfo = {
				"pseudo":ls.get(0).pseudo,
				"score": ls.get(0).score + this.score,
				"difficulty":ls.get(0).difficulty,
				"city": ls.get(0).city,
			}
      console.log(playerInfo);
			await ls.set (0, playerInfo)

      if(this.number>= 10){
        //end of the game
        this.$router.push({'name': 'result'})
      }else{
        //redirection vers la page suivante
        this.time = 0
        this.number++
        this.$router.push({'name': 'geoloc'})
        this.count()
      }

    },

  },
  computed: {

  },
  created: function () {
    this.count();
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "~leaflet/dist/leaflet.css";
.content_test{
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

@media screen and (max-width: 800px) {
  .content_test {
    flex-direction: column-reverse;
  }
  #map, .info{
    margin: 0;
    width : 100%;
  }
}

</style>
