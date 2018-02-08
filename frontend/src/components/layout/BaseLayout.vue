<template>

  <div class="basic-layout">
    <header>
   		 	<div class="bar_nav">
				<img src="../../../assets/images/geoquiz.jpg"/>
	          	<div class="pseudo" v-if="connected">
	            	<strong class="infoParty "> {{pseudo}}
                  <router-link class="buttonHome" to="home()">Accueil</router-link>
                </strong>

	          	</div>
              <div class="pseudo" v-else>
	            	<strong class="infoParty ">
                  <router-link class="buttonHome" to="connexion" >Ajouter un Point</router-link>
                 </strong>
	          	</div>
   		 	</div>
    </header>

    <slot/>
    <footer>
      <p class="footerPara">Site développé par: BISELX Charles, ESCAMILLA Valentin, MEDJMADJ Issam, PENGUILLY Bertrand<br><br>© GEOQUIZ - 2018</p>
    </footer>
  </div>

</template>

<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'
	import ls  from '@/services/ls'
export default {
	data: function () {
	  return {
      connected: false,
      pseudo : "",
      score : 0,
	  };
	},
  methods: {

    home(){
      this.$router.push({'name': 'home'})
    },

    actualise(){
      if(ls.isEmpty(0)){
        this.pseudo = ""
        this.score =  0
      }else {
        this.pseudo =  ls.get(0).pseudo;
        this.score =   ls.get(0).score;
        if(ls.get(0)){ this.connected = true; }
      }

    }
	},
  created: function () {
    this.connected = false ;
    this.actualise();
  }
}
</script>

<style scoped>
	.bar_nav {
		background-color: black;
	  	overflow: hidden;
	  	height: 100px;
	  	width: 100%;
	}
	img{
		height: auto;
		width: 160px;
		float:left;
		margin-left: 5%;
	}
	footer{
		background-color:black;
		color:white;
	}
	p.footerPara {
  		margin-bottom: 0px;
  		padding: 1%;
  		font-weight: bold;
   }
  body{
		min-height: 100%;
		position: relative;
		padding-bottom: 100px;
	}
	html{
		height: 100%;
	}
	.buttonHome{
		padding:5px;
		color:white;
		background-color: green;
		text-align: center;
	}

  .pseudo{
    padding-top : -20px;
    margin: 0;
    display: block;
    height: 100%;
    text-align: right;
  }
  .infoParty{
    display: block;
    vertical-align: center;
    margin: 20px 25px 20px 20px;
    color: white;
  }
</style>
