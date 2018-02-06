<template>

  <div class="basic-layout">
    <header>
   		 	<div class="bar_nav">
					<img src="../../../assets/images/geoquiz.jpg"/>	
					<router-link class="buttonHome" to="home">Accueil</router-link>			
   		 	</div>
    </header>

    <slot/>
    <footer>
      <p class="footerPara">Site développé par: BISELX Charles, ESCAMILLA Valentin, MEDJMADJ Issam, PENGUILLY Bertrand<br><br>© GEOQUIZ - 2018</p>
    </footer>
  </div>

</template>


<style scoped>

	.hello{
		color:white;
		font-weight: bold;
		background-color: black;
	}

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

</style>