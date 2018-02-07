<template>
  <layout-basic>
    <div class="result">
      <h1>RESULTAT</h1>
      <h2>votre resultat : {{finalScore}}</h2>
      <ul   v-for="item in topScore">
        <li><div class="score"> {{item.position}} : <div class="left">{{item.pseudo}}</div> <div class="right">{{item.score}}</div></div></li>
      </ul>
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
  data: function () {
    return {
      finalScore :  "",

      topScore : json,
    };
	},
  methods: {
    home(){
      this.$router.push({'name': 'home'})
    }
  },
  created: function () {
    this.finalScore =  ls.get(0).score;
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
  width: 50%;
  margin-left: 25%;
  margin-bottom: 10px;
  border-style: solid;
  border-width: thin;
  font-weight: bold;
}
.left{
  font-weight: normal;
  margin:0;
  width: 45%;
  display: inline-block;
  text-align: left;
}
.right{
  font-weight:normal;
  margin:0;
  width: 45%;
  display: inline-block;
  text-align: right;
}

</style>
