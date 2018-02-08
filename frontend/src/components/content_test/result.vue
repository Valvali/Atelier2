<template>
  <layout-basic>
    <div class="result">
      <h1>RESULTAT</h1>
      <h2>votre resultat : {{finalScore}}</h2>

      <!--<ul>
        <div v-for="(item) in scores">
          <li><div class="score">01 : <div class="left">{{item.nom}}</div> <div class="right">{{item.score}}</div></div></li>

        </div>-->
      <ul   v-for="item in topScore">
        <li><div class="score"> <div class="left"><span class="strong">{{item.position}} :</span> {{item.pseudo}}</div> <div class="right">{{item.score}}</div></div></li>
      </ul>
      <button class="button is-primary" @click="home">Retour vers l'accueil</button>
    </div>
  </layout-basic>
</template>

<script>
import api from '@/services/api'
import LayoutBasic from '@/components/layout/BaseLayout'
import ls  from '@/services/ls'


import Vue from 'vue'
import json from '../../../assets/donneesScore.json'

export default {
  components: {
      LayoutBasic
  },

  name: 'App',
  data() {
    return {
    scores:[]

  }
},
  data: function () {
    return {
      finalScore :  "",

      topScore : json,
    };
	},
  methods: {
    home(){
      this.$router.push({'name': 'home'})
    },
    getScores() {
      api.get('/score').then(response => {
        // success callback
        this.scores = response.data
      }, response => {
        // error callback
        console.log("error = "+response.message)
      })
    }
  },
  created: function () {
    this.finalScore =  ls.get(0).score;
    this.getScores();
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.result{
  margin-top: 100px;
  margin-bottom: 50px;
}
.result h1{
  font-size: 50px;
  font-weight: bold;
}
.result h2{
  font-size: 25px;
  font-weight: bold;
}
.score{
  background-color: #ffccb3;
  font-size: 20px;
  width: calc(100% - 2 * 20% );
  margin-right: 20%;
  margin-left: 20%;
  margin-bottom: 10px;
  border-style: solid;
  border-width: thin;
}
.strong{
  font-weight: bold;
}
.left{
  margin:0;
  width: 45%;
  display: inline-block;
  text-align: left;
}
.right{
  margin:0;
  width: 45%;
  display: inline-block;
  text-align: right;
}
@media screen and (max-width: 900px) {
  .score{
    width: calc(100% - 2 * 2.5%);
    margin-left: 2.5%;
    margin-right: 2.5%;
  }

}
@media screen and (max-width: 500px) {

    .left{
      width: 100%;
      display: block;
      text-align: center;
    }
    .right{
      width: 100%;
      display: block;
      text-align: center;
    }
}

</style>
