<template>
  <!--  <v-btn @click="placeBidClicked" dark flat>Placera bud</v-btn>-->
  <v-layout row wrap class="bidBar">
    <smooth-picker class="scroller col-6" ref="smoothPicker" :data="possibleBids" :change="dataChange"/>
    <v-btn col-2 class="bidBtn" @click="placeBidClicked">Lägg Bud</v-btn>
  </v-layout>
</template>

<script>
	//import { eventBus } from "@/main";
	import 'vue-smooth-picker/dist/css/style.css';
	import {SmoothPicker} from 'vue-smooth-picker';

	export default {
		name: "PlaceBid",
		components: {
		},
		data() {
			return {
			}
		}
	}
</script>

<style scoped>
  /*.scroller {*/
  /*  width: auto;*/
  /*  height: auto;*/
  /*  bottom: 10px;*/
  /*}*/

  /*.bidBtn {*/
  /*  width: auto;*/
  /*  height: auto;*/
  /*}*/

  /*.bidBar {*/
  /*  width: 300px;*/
  /*  height: auto;*/
  /*}*/
</style>
