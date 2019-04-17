<template>
   <v-container>
      <v-btn @click="placeBidClicked" dark flat>Login</v-btn>
   </v-container>
</template>

<script>
   export default {
      name: "PlaceBid.vue",
      methods: {
         placeBidClicked() {
            this.$router.push({name: 'about'}) // will be something like /auction/...
         }
      }
   }
</script>

<style scoped>

</style>