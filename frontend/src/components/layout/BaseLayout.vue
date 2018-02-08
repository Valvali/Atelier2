<template>

  <div class="basic-layout">
    <header>
   		 	<div class="bar_nav">
				<img src="../../../assets/images/geoquiz.jpg"/>
	          	<div class="pseudo" v-if="connected">
	            	<span class="infoParty "> <span>{{pseudo}}</span>
                  <button class="button is-primary" @click="home()">Accueil</button>
                </span>
	          	</div>
              <div class="pseudo" v-else-if="backOffice">
	            	<span class="infoParty "> <span>{{name}}</span>
                  <button class="button is-primary" @click="logOut()">Deconnexion</button>
                </span>
	          	</div>
              <div class="pseudo" v-else-if="signin">
	            	<span class="infoParty ">
                  <button class="button is-primary" @click="home()">Accueil</button>
                </span>
	          	</div>
              <div class="pseudo" v-else>
	            	<span class="infoParty ">
                  <button class="button is-primary" @click="connexion()" >Ajouter un Point</button>
                 </span>
	          	</div> -->
   		 	</div>
    </header>

    <slot/>
    <footer>
      <p class="footerPara">Site développé par: BISELX Charles, ESCAMILLA Valentin, MEDJMADJ Issam, PENGUILLY Bertrand<br><br>© GEOQUIZ - 2018</p>
    </footer>
  </div>

</template>

<script>
  import { mapGetters } from 'vuex'
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'
	import ls  from '@/services/ls'
  import store from '@/store'


export default {
	data: function () {
	  return {
      name : "",
      signin: false,
      backOffice: false,
      connected: false,
      pseudo : "",
      score : 0
	  }
	},
  computed: mapGetters({isConnected: 'auth/isConnected'}),

  methods: {

    home(){
      this.$router.push({'name': 'home'})
    },
    logOut(){
      //TODO deconnexion
      this.$router.push({'name': 'home'})
    },
    connexion(){
      this.$router.push({'name': 'connection'})
    },

    actualise(){
      if (this.$route.name == "geoloc" || this.$route.name == "result") {
        this.connected = true
      }
      else if (this.$route.name == "connection" || this.$route.name == "inscription") {
        this.signin = true
      }
      else if (this.$route.name == "admin" ) {
        this.backOffice = true
        this.name = store.getters['auth/getConnectedUser']
      }
      else  {
        this.backOffice = false
        this.connected = false
        this.signin = false
      }

      if(ls.isEmpty(0)){
        this.pseudo = ""
        this.score =  0
      }else {
        this.pseudo =  ls.get(0).pseudo;
        this.score =   ls.get(0).score;
      }

    }
	},
  created: function () {
    this.actualise();

    // setTimeout(function(){
    //   console.log(this.isConnected)
    // }, 3000)
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

  .pseudo{
    padding-top : -20px;
    margin: 0;
    display: block;
    height: 100%;
    text-align: right;
  }
  .infoParty{
    font-weight: bold;
    display: block;
    margin: 20px 25px 20px 20px;
    color: white;
  }
  .infoParty button{
    vertical-align: middle;

  }
</style>
