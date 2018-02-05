<template>
  <div class="content_test">

      <v-map id="map" :zoom="zoom" :center="center" :options="option" v-on:l-click="getPoint($event)" >
        <v-tilelayer :url="url" ></v-tilelayer>
      </v-map>
      <div class="photo">
        {{time}}

        <img src="https://www.petitfute.com/medias/professionnel/30049/premium/600_450/223989-nancy-place-stanislas.jpg" alt="photo">
        <div class="description">
          <h3>description</h3>
          <p>lorem ipsum
          </p>
        </div>
      </div>
  </div>
</template>

<script>
import Vue from 'vue'
import api from '@/services/api'

import Vue2Leaflet from 'vue2-leaflet';

Vue.component('v-map', Vue2Leaflet.Map);
Vue.component('v-tilelayer', Vue2Leaflet.TileLayer);
Vue.component('v-marker', Vue2Leaflet.Marker);

export default {
  name: 'App',
  data: function () {
    return {
      zoom: 13,

      center: [48.6833, 6.2],
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      option: { zoomControl: false, dragging: false, doubleClickZoom:false, trackResize:false, minZoom:this.zoom, maxZoom:this.zoom},


      position: L.latLng(48.6833, 6.2),

      time: 0,
      RayonValid: 3000,
    };
	},
  methods: {
    count() {
      console.log("launch");
      let interval = setInterval(()=> {
        this.time++
        if(this.time>30){
          clearInterval(interval);
          this.time = "fin du temps"
        }
      },1000)
    },
    getPoint(e){
      let click = L.latLng(e.latlng.lat,e.latlng.lng);
      //retourne
      let res = (this.RayonValid - this.position.distanceTo(click)) / 2 
      if(res<0){res = 0}
      console.log("distance = "+res);
      console.log("temps = "+this.time);
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
      console.log("score = "+res);
    },

  },
  created: function () {
    this.count();

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "~leaflet/dist/leaflet.css";
#map {
  cursor: pointer;
  height: 700px;
  width : 80%;
}

</style>
