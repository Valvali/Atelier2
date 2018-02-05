<template>
<layout-basic>
  <div class="content_test">
      <v-map id="map" :zoom="zoom" :center="center">
        <v-tilelayer :url="url" ></v-tilelayer>
        <v-marker :lat-lng="marker"></v-marker>
      </v-map>
      <div class="photo">
        <img src="https://www.petitfute.com/medias/professionnel/30049/premium/600_450/223989-nancy-place-stanislas.jpg" alt="photo">
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
import Vue from 'vue'
import api from '@/services/api'
import LayoutBasic from '@/components/layout/BaseLayout'
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
  height: 700px;
  width : 80%;
}

</style>
