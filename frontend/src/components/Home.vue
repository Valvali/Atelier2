<template>
	<layout-basic>
	    <div id="app" class="container">
	    
		    <section>		       
		            <br><span><b>Ville: </b></span><b-input class="input" placeholder="Entrez votre ville"></b-input>
		            <br><span><b>Difficulté: </b></span><b-input class="input" type="Number" placeholder="Entrez le niveau de difficulté"></b-input>
		        	<br><span><b>Username: </b></span><b-input class="input" placeholder="Entrez votre username"></b-input>
		        <br><a class="button is-link is-active">Active</a>
		    </section>

		</div>
	</layout-basic>
</template>
s
<script>
	import api from '@/services/api'
	import LayoutBasic from '@/components/layout/BaseLayout'

	export default {
		components: {
	      LayoutBasic
	    }
	}
</script>

<style scoped>
	.input{
		width: 40%;
		padding: 0;
	}
	.input{
		margin-bottom: 3%;
	}
	.label{
		margin-top:3%;
	}

</style>