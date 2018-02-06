<template>
  <layout-basic>
    <div class="content_test">

        <v-map id="map" :zoom="zoom" :center="center" :options="option" v-on:l-click="getPoint($event)" >
          <v-tilelayer :url="url" ></v-tilelayer>
        </v-map>
        <div class="info">
          <h1 class="note">N° : {{number}} </h1>
          <h1 class="note">Score : {{score}} </h1>
          <h1 class="note">Temps : {{time}} </h1>

          <img class="tips" src="https://www.petitfute.com/medias/professionnel/30049/premium/600_450/223989-nancy-place-stanislas.jpg" alt="photo">
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
import Vue from 'vue'
import Vue2Leaflet from 'vue2-leaflet';
import LayoutBasic from '@/components/layout/BaseLayout'

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
      center: [48.6833, 6.2],
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      option: { zoomControl: false, dragging: false, doubleClickZoom:false, trackResize:false, minZoom:this.zoom, maxZoom:this.zoom},

      number : 0,
      position: L.latLng(48.6833, 6.2),

      score:0,
      time: 0,
      RayonValid: 2000,
      interval: null,
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
    getPoint(e){
      //need stop interval
      clearInterval(this.interval);
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
      this.score += res
      console.log("score = "+res);

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
