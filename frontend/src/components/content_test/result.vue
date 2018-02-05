<template>
  <layout-basic>
    <div class="result">
      <h1>RESULTAT</h1>
      <ul>
        <li><div class="score">01 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">02 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">03 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">04 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">05 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">06 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">07 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">08 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">09 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>
        <li><div class="score">10 : <div class="left">AAAAA</div> <div class="right">3461658</div></div></li>


      </ul>
    </div>
  </layout-basic>
</template>

<script>
import api from '@/services/api'
import LayoutBasic from '@/components/layout/BaseLayout'

import Vue from 'vue'

export default {
  components: {
      LayoutBasic
  },

  name: 'App',
  data: function () {
    return {

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
.result{
  margin-top: 100px;
  margin-bottom: 50px;
}
.result h1{
  font-size: 50px;
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

</style>
