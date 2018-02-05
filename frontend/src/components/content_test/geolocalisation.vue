<template>
  <div class="content_test">
      <v-map id="map" :zoom="zoom" :center="center">
        <v-tilelayer :url="url" ></v-tilelayer>
        <v-marker :lat-lng="marker"></v-marker>
      </v-map>
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
      center: [47.413220, -1.219482],
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      marker: L.latLng(48.56420, -1.219482),
    };
	},
  methods: {

  },
  created: function () {

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "~leaflet/dist/leaflet.css";
#map {
  background-color: red;
  height: 800px;
  width : 800px;
}
</style>
