<template>
   <v-container>
      <v-btn @click="loginClicked" dark flat>Login</v-btn>
   </v-container>
</template>

<script>
   export default {
      name: "LoginNav",
      methods: {
         loginClicked() {
            this.$router.push({name: 'login'})
         }
      }
   }
</script>

<style scoped>

</style>